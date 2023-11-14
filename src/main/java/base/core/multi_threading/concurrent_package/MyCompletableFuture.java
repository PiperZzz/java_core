package base.core.multi_threading.concurrent_package;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.scheduling.annotation.Async;

/* CompletableFuture的一个重要特性是它可以将多个CompletableFuture串联起来组合成一个新的CompletableFuture，以便处理异步任务之间的依赖关系
* 无论是以前的Future接口，还是Spring的@Async都难以完成这类复合异步任务
*/
public class MyCompletableFuture {
    /* InterruptedException：总体来说这是一种线程被意外中断时发生的异常
     * ExecutionException：一般会发生在被要求被异步执行的方法内部，发生的异常会被包装向上抛出
     */
    public static void parallelCompletableFuture() throws InterruptedException, ExecutionException {
        /* 创建三个CompletableFuture对象，每个代表一个外部服务的调用
         * supplyAsync()方法接收一个Supplier<T>类型的参数，返回一个CompletableFuture<T>对象
         * 它的作用是把外部服务的调用放到一个线程池中执行，不会阻塞当前线程
         */
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> callService1());
        /* CompletableFuture的Generics必须和supplyAsync()方法参数中的方法返回类型相同
         * 这个返回值会被用来判断CompletableFuture是否已经完成，也就是get()方法所期待返回的类型
         * 由于supplyAsync()方法是一个非阻塞方法，意味着它会立即返回，而不会等待外部服务的调用完成，此时CompletableFuture service1的状态是未知的
         */
        CompletableFuture<String> service2 = CompletableFuture.supplyAsync(() -> callService2());
        CompletableFuture<String> service3 = CompletableFuture.supplyAsync(() -> callService3());

        /* 再创建一个CompletableFuture，用于等待所有的外部服务调用完成
         * allOf()方法接收一个CompletableFuture型数组，返回一个CompletableFuture<Void>对象，作为所有CompletableFuture的完成信号
         */
        CompletableFuture<Void> allOf = CompletableFuture.allOf(service1, service2, service3);
        /* 这种将多个异步方法合并的方式，所有方法的优先级都是一样的。也就是说service1、service2、service3可能以任何顺序完成。
         * 最终完成合并的CompletableFuture对象的作用就是担保所有的异步方法都已经完成，不管是正常完成还是异常完成。
         * 这点是以前的Future接口无法做到的。也是Spring的@Async无法做到的。
         */

        /* 阻塞等待所有外部服务调用完成 */
        allOf.join();

        /* 在调用get()方法之前，service1、service2、service3的状态依然是不确定的
         * 但是由于之前用join()阻塞等待所有外部服务调用完成，所以在get()方法被调用，期待的是CompletableFuture内的Generics对象已经可以被get()返回了
         * get()是一个阻塞方法，它会强制阻塞当前线程，等待CompletableFuture内的Generics对象被返回
         * 如果CompletableFuture内的Generics对象(也就是外部服务）发生了异常，get()会将异常包装成ExecutionException抛出
         * 如果get()自己被意外中断，发生的异常就是InterruptedException
         */
        String result = service1.get() + service2.get() + service3.get();
        System.out.println("Combined Result: " + result);
    }

    public static void chainCompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> callService1());
        CompletableFuture<String> service2 = service1.thenApplyAsync((result) -> result + callService2());
        CompletableFuture<String> service3 = service2.thenApplyAsync((result) -> result + callService3());

        String result = service3.get();
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
