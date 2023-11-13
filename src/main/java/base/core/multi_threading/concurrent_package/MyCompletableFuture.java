package base.core.multi_threading.concurrent_package;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.scheduling.annotation.Async;

/* CompletableFuture的一个重要特性是它可以将多个CompletableFuture串联起来组合成一个新的CompletableFuture，以便处理异步任务之间的依赖关系
* 无论是以前的Future接口，还是Spring的@Async都难以完成这类复合异步任务
*/
public class MyCompletableFuture {
    /* InterruptedException
     * ExecutionException
     */
    public static void parallelCompletableFuture() throws InterruptedException, ExecutionException {
        /*  */
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> callService1());
        CompletableFuture<String> service2 = CompletableFuture.supplyAsync(() -> callService2());
        CompletableFuture<String> service3 = CompletableFuture.supplyAsync(() -> callService3());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(service1, service2, service3);
        allOf.join();

        /* 在get()方法被调用 */
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
