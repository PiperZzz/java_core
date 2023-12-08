package base.core.spring_webflux.webclient;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import reactor.core.publisher.Mono;

@Service
public class MyWebClientReactorMono {
    private WebClient webClient;
    private String jwtToken;

    @Value("${security.access.token}")
    private String accessToken;

    public MyWebClientReactorMono(WebClient.Builder webClientBuilder) {
        // 在构造函数中创建 WebClient 实例并配置基本信息
        this.webClient = webClientBuilder
                .baseUrl("https://example.com")
                /* 使用默认的静态令牌 */
                .defaultHeader(HttpHeaders.AUTHORIZATION, accessToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public MyWebClientReactorMono(WebClient.Builder webClientBuilder, String jwtToken) {
        // 在构造函数中创建 WebClient 实例并配置基本信息
        this.webClient = webClientBuilder
                .baseUrl("https://example.com")
                .build();

        this.jwtToken = jwtToken;
    }


    public Mono<String> fetchDataFromEndpoint1() {
        return webClient.get()
                .uri("/endpoint1")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> fetchDataFromEndpoint2() {
        return webClient.get()
                .uri("/endpoint2")
                .headers(requestHeaders -> requestHeaders.setBearerAuth(jwtToken))
                .retrieve()
                .bodyToMono(String.class);
    }
}
