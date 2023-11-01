package base.core.multi_threading.concurrent_package;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MyCompletableFuture {
    /* CompletableFuture的一个重要特性是
    它可以将多个CompletableFuture串联起来
    组合成一个新的CompletableFuture
    以便处理异步任务之间的依赖关系 */

    public static void exmapleCompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> callService1());
        CompletableFuture<String> service2 = CompletableFuture.supplyAsync(() -> callService2());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(service1, service2);
        allOf.join();

        String result = service1.get() + service2.get();
        System.out.println("Combined Result: " + result);
    }

    private static String callService1() {
        // Simulate calling an external service
        return "Service 1 Response ";
    }

    private static String callService2() {
        // Simulate calling another external service
        return "Service 2 Response ";
    }
}
