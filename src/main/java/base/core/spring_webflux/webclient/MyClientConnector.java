// package base.core.spring_webflux.webclient;

// import java.net.URI;
// import java.util.function.Function;

// import org.springframework.http.client.reactive.ClientHttpConnector;
// import org.springframework.http.client.reactive.ClientHttpRequest;
// import org.springframework.http.client.reactive.ClientHttpResponse;
// import org.springframework.http.HttpMethod;

// import reactor.core.publisher.Mono;
// import reactor.netty.tcp.TcpClient;
// import reactor.netty.http.client.HttpClient;

// import io.netty.channel.ChannelOption;

// public class MyClientConnector implements ClientHttpConnector {
    
//     @Override
//     public Mono<ClientHttpResponse> connect(HttpMethod method, URI uri, Function<? super ClientHttpRequest, Mono<Void>> requestCallback) {
//         return HttpClient.create()
//             .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) 
//             .request(io.netty.handler.codec.http.HttpMethod.GET)// 设置HTTP请求方法 代码有问题io.netty.handler.codec.http.HttpMethod和import org.springframework.http.HttpMethod;
//             .uri(uri) // 设置请求URI
//             .headers(headers -> headers.add("User-Agent", "Custom User Agent")) // 添加自定义请求头
//             .send(requestCallback) // 发送请求
//             .responseSingle((responseInfo, byteBufMono) ->
//                 Mono.just(new CustomClientHttpResponse(responseInfo, byteBufMono))); // 处理响应
//     }
// }
