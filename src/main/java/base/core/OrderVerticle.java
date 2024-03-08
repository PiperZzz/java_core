package base.core;

import org.springframework.beans.factory.annotation.Autowired;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class OrderVerticle extends AbstractVerticle{
    
    @Autowired
    private OrderService orderService;

    @Override
    public void start() {
        Vertx vert = Vertx.vertx();
        HttpServer server = vert.createHttpServer();
        Router router = Router.router(vertx);

        router.put("/api/v1/order/create").handler(this::createOrder);
        router.get("/api/v1/orders/get").handler(this::getOrderHandler);
        router.post("/api/v1/orders/update/:orderId").handler(this::updateOrderHandler);
        router.delete("/api/v1/orders/cancel/:orderId").handler(this::cancelOrderHandler);

        server.requestHandler(router).listen(8080);
    }

    private void createOrder(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        request.bodyHandler(buffer -> {
            OrderDTO order = new OrderDTO();
            order.setOrderId(buffer.toJsonObject().mapTo(OrderDTO.class).getOrderId());
            orderService.createOrder(order);
            routingContext.response().end("Order created");
        });
    }

    private void getOrderHandler(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        Long orderId = Long.parseLong(request.getParam("orderId"));
        OrderDTO order = orderService.getOrder(orderId);
        routingContext.response().end(order.toString());
    }

    private void updateOrderHandler(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        Long orderId = Long.parseLong(request.getParam("orderId"));
        request.bodyHandler(buffer -> {
            OrderDTO order = new OrderDTO();
            order.setOrderId(buffer.toJsonObject().mapTo(OrderDTO.class).getOrderId());
            OrderDTO newOrder = orderService.updateOrder(orderId, order);
            routingContext.response().end("Order updated");
        });
    }

    private void cancelOrderHandler(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        Long orderId = Long.parseLong(request.getParam("orderId"));
        orderService.cancelOrder(orderId);
        routingContext.response().end("Order cancelled");
    }
}
