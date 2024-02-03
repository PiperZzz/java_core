package base.core.multi_threading.concurrent_package;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class MyThread extends Thread {
    private Callback callback; /* extends Thread创建异步任务的方式必须有用于获得run()方法中外部服务返回结果的回调 */

    private static ExecutorService customThreadPoolByExecutorService = Executors.newFixedThreadPool(3);
    /* 用CompletableFuture如果不显式手动配置线程池，那么会使用系统默认的ForkJoinPool，这个线程池可以被多个CompletableFuture共享
     * 如果用extends Thread的方式，根本不会用到线程池，而是直接创建一个新线程
     */
    
    public MyThread(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        /* extends Thread创建异步任务的方式必须重写Thread类中run()方法，用于指定需要异步执行的外部任务 */
        String result = callExternalService();
        /* 重写run()方法是只是分配实际需要执行的任务，它并不能保证该任务会由一个新的线程执行。而start()方法才是为这个run()方法内的任务开辟一个新的线程的终极方法。 */
        callback.onComplete(result); // 调用回调函数，将结果传递给回调
    }

    public interface Callback {
        void onComplete(String result);
    }

    /* 用extends Thread的方式，手动分配给异步执行的外部服务给单一新线程
     * 这种方法最为古早，就相当于自己一个线程一个线程地手动创建线程池，
     * 由于线程池里地每个线程都是用这种方式手动创建地，所以线程的生命周期需要手动管理，非常不推荐
    */
    public static void asyncTaskExtendsThread() throws ExecutionException {
        MyThread myThread = new MyThread(result -> {
            /* 在回调函数中处理callExternalService()的结果 */
            System.out.println("Result: " + result);
        });
        myThread.start(); // 只有当start()方法被调用，才会真正创建一个新线程，执行run()方法中的任务
        // 由于start()方法是非阻塞方法，它会将myThread内run()方法中的任务分配给一个新线程，然后立即返回，不会阻塞主线程

        /*
        * 在这里可以执行其他任务，不会阻塞主线程 
        */

        try {
            /* 阻塞主线程，等待回调结果 */
            myThread.join(); // join()方法是阻塞方法，它会阻塞当前线程，让myThread内run()方法中的任务执行完毕，然后回到当前线程的任务
        } catch (InterruptedException e) {
            /* 如果方法asyncTaskExtendsThread()声明的时候不Throws InterruptionException，就必须在这里捕获这个异常 */
            e.printStackTrace();
        }
    }

    /* Callable + FutureTask + new Thread()也是一种非常古早的单一线程实现方式 */
    public static void aysncCallableFutrueTaskNewThread() throws InterruptedException, ExecutionException {
        Callable<String> callableTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return callExternalService();
            }
        };

        FutureTask<String> futureTask = new FutureTask<>(callableTask);

        /* 虽然可以把FutureTask对象放入线程池，但这极为不常见
         * 因为FutureTask对象的作用就是为了包装Callable对象，将其放入单个线程中执行的
         */
        Thread thread = new Thread(futureTask);

        thread.start();

        thread.join();

        String result = futureTask.get();

        System.out.println("Result: " + result);
    }

    /* Callable + Future + ThreadPool的实现方式，比用单一线程先进了不少 */
    public static void aysncCallableFutrueThreadPool() throws InterruptedException, ExecutionException {
        Callable<String> callableTask = () -> callExternalService();

        Future<String> future = customThreadPoolByExecutorService.submit(callableTask);
        // submit()方法和exectue()方法的区别在于，submit()方法会返回Future对象来跟踪异步任务的执行情况

        String result = future.get();

        System.out.println("Result: " + result);
    }

    /* 用CompletableFuture完成Callable + Future + ThreadPool相同的任务
     * 目前最为先进的实现方式，不仅可以完成简单异步任务，还可以完成各种复杂的依赖合并异步任务
     */
    public static void asyncTaskCompletableFutureSupply() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> callExternalService(), customThreadPoolByExecutorService);

        completableFuture.join(); // 阻塞主线程，等待异步任务完成   

        String result = completableFuture.get(); // 得到异步任务的执行结果

        System.out.println("Result: " + result);
    }

    /* Runnable古早的单一线程实现方式 */
    public static void asyncRunnableNewThead() throws InterruptedException, ExecutionException {
        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                callExternalService();
            }
        };

        Thread thread = new Thread(runnableTask);

        thread.start();
        /* 原则上不调用thread.join()等待异步任务完成，不然就和同步任务有区别了
         * 因为Runnable对象的run()方法没有返回值，所以也就没有办法获取异步任务的执行结果
         * Runnable的目的就是完全不关心callExternalService()的异步执行结果
         */
    }

    /* Runnable比单一线程实现方法更先进的ThreadPool实现方式 */
    public static void asyncRunnableThreadPool() throws InterruptedException, ExecutionException {
        Runnable runnableTask = () -> callExternalService();

        customThreadPoolByExecutorService.execute(runnableTask);
        // exectue()方法和submit()方法的区别在于，execute()方法不会返回任何Future对象，所以无法判断任务是否执行成功
    }
    
    /* 最先进的CompletableFuture实现方式 */
    public static void asyncTaskCompletableFutureRun() throws InterruptedException, ExecutionException {
        CompletableFuture.runAsync(() -> callExternalService(), customThreadPoolByExecutorService);

        /*
        * 在这里可以执行其他任务，不会阻塞主线程 
        */

        /* 原则上不调用join()，因为runAsync()目的就是不关心异步任务的执行结果 */
    }

    /* 用CompletableFuture，完成相同的任务，同时还可以提供非阻塞方法等待回调结果 */
    public static void asyncTaskCompletableFutureThenAccept() throws InterruptedException, ExecutionException {
        ExecutorService localCustomThreadPoolByExecutorService = Executors.newFixedThreadPool(3);
        
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> callExternalService(), localCustomThreadPoolByExecutorService);

        /* 
        * 在这里可以执行其他任务，不会阻塞主线程 
        */

        completableFuture.thenAccept((String result) -> {
            // CompletableFuture还提供了非阻塞方法，在异步任务完成后执行回调，不会阻塞主线程
            System.out.println("Result: " + result);
        });

        /* 关于线程池
         * 如果用类成员共享线程池就不要在方法里手动关闭线程池，因为其它方法可能还需要用到这个线程池，这个线程池的生命周期应该由类成员管理
         * 如果用局部变量线程池就必须在方法里手动关闭线程池，因为这个线程池的生命周期只在这个方法里，方法结束后就应该显式关闭线程池
         * 这里又是一个CompletableFuture比Callable和Runnable更先进的地方，如果不手动指定线程池，CompletableFuture可以自动使用默认的全局线程池ForkJoinPool,完全不用担心线程池的生命周期
         * 而Callable和Runnable必须手动指定线程池，否则就会使用默认的单一线程池，这样就必须手动管理线程池的生命周期
         */
        localCustomThreadPoolByExecutorService.shutdown();
    }

    public static String asyncService(Supplier<String> externalServiceCall) throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(externalServiceCall);

        return completableFuture.get();
    }

    public static <T> T asyncServiceT(Supplier<T> externalServiceCall) throws InterruptedException, ExecutionException {
        CompletableFuture<T> completableFuture = CompletableFuture.supplyAsync(externalServiceCall);

        return completableFuture.get();
    }

    private static String callExternalService() {
        // 模拟调用外部服务1，并返回响应
        return "External Service Response";
    }
}

