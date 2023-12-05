package base.core.basic.java8newer;

import java.net.http.HttpClient;

public class MyHttpClient {
    private final HttpClient httpClient;
    private final HttpClient httpClientGlobal;

    public MyHttpClient() {
        this.httpClient = HttpClient.newBuilder().build(); //可自定义配置的局部HttpClient实例创建方法

        this.httpClientGlobal = HttpClient.newHttpClient(); //默认的HttpClient实例创建方法，全局共享，无法自定义配置
    }
}
