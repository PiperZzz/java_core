package base.core.multi_threading.concurrent_package;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Async;

/* CompletableFuture的一个重要特性是它可以将多个CompletableFuture串联起来组合成一个新的CompletableFuture，以便处理异步任务完成后的依赖关系
 * 无论是以前的Future接口，还是Spring的@Async都难以完成这类“复合”异步任务
 */
public class MyCompletableFuture {
    /* 用CompletableFuture如果不显式手动配置线程池，那么会使用系统默认的ForkJoinPool，这个线程池可以被多个CompletableFuture共享 */
    private static ExecutorService customThreadPoolByExecutorService = Executors.newFixedThreadPool(3);

    /* InterruptedException：通常在线程被意外中断时发生，不包含任何cause，自己本身就是异常的cause
     * 如果方法不抛出InterruptedException，编译器会强制要求在调用get()时，用catch语句中处理这个异常
     * ExecutionException：一般会发生在被要求被异步执行的方法内部，发生的异常会被包装向上抛出，通常用getCause()方法获取到原始的异常
     * 如果方法不抛出ExecutionException，编译器会强制要求在调用get()时，用catch语句中处理这个异常
     */
    public static void supplyAsyncMethod() throws InterruptedException, ExecutionException {
        /* 创建三个CompletableFuture对象，每个代表一个外部服务的调用
         * supplyAsync()是非阻塞方法，接收一个Supplier<T>类型的参数，返回一个CompletableFuture<T>对象
         * 所以CompletableFuture的Generics必须和supplyAsync()方法参数中的方法返回类型一致
         */
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> callService1(), customThreadPoolByExecutorService);
        /* supplyAsync()的作用是把外部服务callService1()的调用移交给线程池中的某个线程来执行，达到不阻塞当前线程的目的
         * 这个动作实际上有两步：1、将callService1()推入异步状态；2、成功之后立即返回一个状态未知的CompletableFuture对象service1来跟踪callService1()的执行结果。
         * 理论上，这两步之间是可以被打断的，但实际上supplyAsync()方法的执行完整性是非常可信的  
         * 由于supplyAsync()方法是一个非阻塞方法，意味着它会立即返回，而不会等待外部服务的调用完成，所以此时CompletableFuture对象service1的状态必然是未知的
         * 它等待着后续get()或join()阻塞方法的调用来确定自己的状态
         */
        CompletableFuture<String> service2 = CompletableFuture.supplyAsync(() -> callService2());
        CompletableFuture<String> service3 = CompletableFuture.supplyAsync(() -> callService3());

        /* 再创建一个CompletableFuture，用于等待所有的外部服务调用完成
         * allOf()是非阻塞方法，接收若干CompletableFuture类型的参数，返回一个CompletableFuture<Void>对象，作为所有被当成参数传入的CompletableFuture对象的完成总信号
         * <Void>是泛型通配符，意思是它不关心Generics的具体类型，但是又必须得有一个Generics类型占位置
         */
        CompletableFuture<Void> allOf = CompletableFuture.allOf(service1, service2, service3);
        /* 这种将多个异步方法合并的方式，所有方法的优先级都是一样的。也就是说service1、service2、service3可能以任何顺序完成。
         * 最终完成合并的CompletableFuture对象的作用就是将所有的异步方法的执行情况由一个CompletableFuture对象allOf统一跟踪。
         * 这点是以前的Future接口无法做到的。也是Spring的@Async无法做到的。
         */

        /* 阻塞等待所有外部服务调用完成 */
        allOf.join();

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(service1, service2, service3);
        /* 阻塞等待任意一个外部服务调用完成 */
        anyOf.join();

        /* 在调用get()方法之前，service1、service2、service3的状态依然是不确定的
         * 但是由于之前用join()阻塞等待所有外部服务调用完成，所以在get()方法被调用，期待的是CompletableFuture内的Generics对象已经可以被get()返回了
         * 这里可能是执行成功返回预期中的Generics对象，也可能是执行失败返回异常
         * get()是阻塞方法，它会强制阻塞当前线程，等待CompletableFuture内的Generics对象被返回
         * 如果外部服务在返回结果给CompletableFuture内的Generics对象的过程中发生了异常，get()会将异常包装成ExecutionException抛出
         * 如果get()自己被意外中断，发生的异常就是InterruptedException
         */
        String result = service1.get() + service2.get() + service3.get();
        System.out.println("Combined Result: " + result);
    }

    public static void thenApplyMethod() throws InterruptedException, ExecutionException {
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> callService1());
        CompletableFuture<String> service1Processed = service1.thenApply(result -> result + "Some Sync Task Results" + callService2());
        /* thenApply()通常是对自己的异步任务完成后的结果进行加工，但是和thenCompose()的不同是它不接受任何其它的异步任务结果 */

        CompletableFuture<String> service2 = CompletableFuture.supplyAsync(() -> callService2());
        CompletableFuture<String> service1CombineServer2 = service1.thenApply((result) -> result + service2.join());
        /* thenApply()如果一定要接受异步任务结果，就必须阻塞等待其变成“同步”*/
        CompletableFuture<String> service3 = service1CombineServer2.thenApplyAsync((result) -> result + callService3(), customThreadPoolByExecutorService);
        /* thenApplyAsync()和thenApply()的不同在于它会给新的CompletableFuture分配一个新的Thread，而不是继续使用这个异步任务链上的Thread，如果不显式配置ThreadPool，会用系统默认的ForkJoinPool */
        String result = service3.get();
        System.out.println("Combined Result: " + result);
    }

    public static void thenComposeMethod() throws InterruptedException, ExecutionException {
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> callService1());

        CompletableFuture<String> service2 = service1.thenCompose((result) -> CompletableFuture.supplyAsync(() -> result + callService2()));
        /* thenCompose()和thenApply()的不同在于它接受其它异步任务，并且合并到它自己的异步任务完成后的结果，这种合并是有依赖的，它依赖于其它异步任务的执行结果 */
        CompletableFuture<String> service3 = service2.thenComposeAsync((result) -> CompletableFuture.supplyAsync(() -> result + callService3()));

        String result = service3.get();
        System.out.println("Combined Result: " + result);
    }

    public static void thenAcceptMethod() throws InterruptedException, ExecutionException {
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> callService1());

        CompletableFuture<Void> service2 = service1.thenAccept((result) -> System.out.println("Result: " + result));
        /* thenAccept()和thenApply()的不同在于它不返回任何结果，它只是对自己的异步任务完成后的结果进行消费 */
        CompletableFuture<Void> service3 = service2.thenAcceptAsync((result) -> System.out.println("Result: " + result), customThreadPoolByExecutorService);

        service3.get();
    }

    public static void thenCombineMethod() throws InterruptedException, ExecutionException {
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> callService1());
        CompletableFuture<String> service2 = CompletableFuture.supplyAsync(() -> callService2());

        CompletableFuture<String> service3 = service1.thenCombine(service2, (result1, result2) -> result1 + result2).exceptionally(null);
        /*  */
        CompletableFuture<String> service4 = service3.thenCombineAsync(service1, (result1, result2) -> result1 + result2, customThreadPoolByExecutorService);

        String result = service4.get();
        System.out.println("Combined Result: " + result);
    }

    @Async
    public CompletableFuture<String> asyncCallService1() {
        /* @Async 更适合这种进入异步的方法之间没有依赖的情况
         * 外部服务1、2、3之间没有执行结果的依赖关系，所以可以并行调用
         */
        return CompletableFuture.supplyAsync(() -> callService1());
    }

    @Async
    public CompletableFuture<String> asyncCallService2() {
        /* @Async 更适合这种进入异步的方法之间没有依赖的情况
         * 外部服务1、2、3之间没有执行结果的依赖关系，所以可以并行调用
         */
        return CompletableFuture.supplyAsync(() -> callService2());
    }

    @Async
    public CompletableFuture<String> asyncCallService3() {
        /* @Async 更适合这种进入异步的方法之间没有依赖的情况
         * 外部服务1、2、3之间没有执行结果的依赖关系，所以可以并行调用
         */
        return CompletableFuture.supplyAsync(() -> callService3());
    }
    /* 非常适合用这种异步模式执行的任务有：
     * 
     */

    private static String callService1() {
        // 模拟调用外部服务1，并返回响应
        return "Service 1 Response ";
    }

    private static String callService2() {
        // 模拟调用外部服务2，并返回响应
        return "Service 2 Response ";
    }

    private static String callService3() {
        // 模拟调用外部服务3，并返回响应
        return "Service 3 Response ";
    }
}
