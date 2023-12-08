package base.core.spring_webflux.webclient;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MyWebClientReactorFlux {
    private WebClient webClient1;
    private WebClient webClient2;
    private WebClient webClient3;

    public MyWebClientReactorFlux(WebClient.Builder webClientBuilder) {
        this.webClient1 = webClientBuilder.defaultHeader("Authorization", "Bearer token1").build();
        this.webClient2 = webClientBuilder.defaultHeader("Authorization", "Bearer token2").build();
        this.webClient3 = webClientBuilder.defaultHeader("Authorization", "Bearer token3").build();
    }

    public Flux<String> fetchDataFromMultipleSources() {
        return Flux.merge(
                fetchDataFromSource1(), 
                fetchDataFromSource2(),
                fetchDataFromSource3()
        ).log();
    }

    private Mono<String> fetchDataFromSource1() {
        return webClient1.get()
                .uri("https://source1.com/data")
                .retrieve() 
                .bodyToMono(String.class);
    }

    private Mono<String> fetchDataFromSource2() {
        return webClient2.get()
                .uri("https://source2.com/data")
                .retrieve()
                .bodyToMono(String.class);
    }

    private Mono<String> fetchDataFromSource3() {
        return webClient3.get()
                .uri("https://source3.com/data")
                .retrieve()
                .bodyToMono(String.class);
    }
}
