package base.core.spring_webflux.webclient;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MyWebClientReactor {
    WebClient webClient1 = WebClient.create();
    WebClient webClient2 = WebClient.create("https://example.com/api/resource");
    
    /* 工业推荐用builder()的方式创建WebClient对象，可以定制客户端行为 */
    WebClient webClient = WebClient.builder()
                                    .baseUrl("https://example.com/api/resource")
                                    // .clientConnector(new CustomClientConnector()) // 自定义连接器是比较常见的将一组预设客户端行为封装起来的方式
                                    // .filter(new CustomLoggingFilter()) // 添加自定义的日志拦截器
                                    .defaultHeader("Authorization", "Bearer token123") // 设置默认的请求头
                                    // .responseTimeout(Duration.ofSeconds(10)) // 设置超时时间为10秒
                                    .build();

    public void webClientGet() {
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/resource")
                    .queryParam("param1", "value1")
                    .queryParam("param2", "value2")
                    .build())
                .cookie("sessionCookie", "sessionId")
                .header("X-Custom-Header", "value")
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                clientResponse -> Mono.error(new Exception("Request failed"))) // 自定义异常处理
                .bodyToMono(String.class)
                .log();

        response.subscribe(
            responseBody -> {
                System.out.println("响应内容：" + responseBody);
            },
            error -> {
                System.err.println("请求失败：" + error.getMessage());
            }
        );
    }
    
}
