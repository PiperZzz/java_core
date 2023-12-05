package base.core.basic.java8newer;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.net.URI;

import java.io.IOException;

public class MyHttpClient {
    private final HttpClient httpClient;
    private final HttpClient httpClientGlobal;

    public MyHttpClient() {
        this.httpClient = HttpClient.newBuilder().build(); //可自定义配置的局部HttpClient实例创建方法

        this.httpClientGlobal = HttpClient.newHttpClient(); //默认的HttpClient实例创建方法，Application全局共享，无法自定义配置
    }

    public void httpClientTest() throws InterruptedException, ExecutionException, IOException {
        String uri = "https://jsonplaceholder.typicode.com/posts/1";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        CompletableFuture<HttpResponse<String>> responseFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // 处理异步响应
        responseFuture.thenAccept(response -> {
            // 处理响应
            int statusCode = response.statusCode();
            System.out.println("Response Code: " + statusCode);

            String responseBody = response.body();
            System.out.println("Response Body: " + responseBody);

            // 在这里可以进行进一步的处理
        });

        // 继续执行其他代码
        System.out.println("Async request sent. Continuing with other tasks...");
        
        // 为了演示目的，等待异步操作完成（实际应用中可以根据需要处理）
        responseFuture.join();

        HttpResponse<String> httpResponse = responseFuture.get();

        // responseFuture.exceptionally(throwable -> {
        //     // 处理异常
        //     System.out.println("Exception occurred: " + throwable.getMessage());
        //     return null;
        // });
    }
}
