package base.core.multi_threading.concurrent_package;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThread extends Thread {

    /* extends Thread创建异步任务的方式必须有用于获得run()方法中外部服务返回结果的回调 */
    private Callback callback;

    /* 用CompletableFuture如果不显式手动配置线程池，那么会使用系统默认的ForkJoinPool，这个线程池可以被多个CompletableFuture共享
     * 如果用extends Thread的方式，根本不会用到线程池，而是直接创建一个新线程
     */
    private static ExecutorService customThreadPoolByExecutorService = Executors.newFixedThreadPool(3);

    public MyThread(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        /* extends Thread创建异步任务的方式必须重写Thread类中run()方法，用于指定需要异步执行的外部任务 */
        String result = callExternalService();
        callback.onComplete(result); // 调用回调函数，将结果传递给回调
    }

    public interface Callback {
        void onComplete(String result);
    }

    /* 用extends Thread的方式，手动分配新线程一个异步执行的外部服务 */
    public static void asyncTaskExtendsThread() throws InterruptedException, ExecutionException {
        MyThread myThread = new MyThread(result -> {
            /* 在回调函数中处理callService1()的结果 */
            System.out.println("Result: " + result);
        });
        myThread.start();

        /*
        * 在这里可以执行其他任务，不会阻塞主线程 
        */

        try {
            /* 阻塞主线程，等待回调结果 */
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void aysncCallableFutrue() throws InterruptedException, ExecutionException {
        Callable<String> callableTask = () -> callExternalService();

        Callable<String> callableTask1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return callExternalService();
            }
        };

        Future<String> future = customThreadPoolByExecutorService.submit(callableTask);

        String result = future.get(); // 阻塞主线程，等待异步任务完成并获取结果

        System.out.println("Result: " + result);
    }

    public static void asyncRunnable() throws InterruptedException {
        Runnable runnableTask = () -> callExternalService();

        customThreadPoolByExecutorService.submit(runnableTask);

        // 关闭线程池
        customThreadPoolByExecutorService.shutdown();
    }

    /* 用CompletableFuture，完成相同的任务 */
    public static void asyncTaskCompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> callExternalService(), customThreadPoolByExecutorService);

        /* 
        * 在这里可以执行其他任务，不会阻塞主线程 
        */

        String result = completableFuture.join(); // 阻塞主线程，等待异步任务完成并获取结果    

        System.out.println("Result: " + result);
    }

    /* 用CompletableFuture，完成相同的任务，同时还可以提供非阻塞方法等待回调结果 */
    public static void asyncTaskCompletableFuture2() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> callExternalService(), customThreadPoolByExecutorService);

        /* 
        * 在这里可以执行其他任务，不会阻塞主线程 
        */

        completableFuture.thenAccept((String result) -> {
            // CompletableFuture还提供了非阻塞方法，在异步任务完成后执行回调，不会阻塞主线程
            System.out.println("Result: " + result);
        });
    }

    private static String callExternalService() {
        // 模拟调用外部服务1，并返回响应
        return "External Service Response";
    }
}

