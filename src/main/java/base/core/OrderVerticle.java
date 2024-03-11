package base.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.Future;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class OrderVerticle extends AbstractVerticle{
    
    private final Logger logger = LoggerFactory.getLogger(OrderVerticle.class);
    private final OrderService orderService;

    public OrderVerticle(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void start() {
        Vertx vert = Vertx.vertx();
        HttpServer server = vert.createHttpServer();
        Router router = Router.router(vertx);

        router.put("/api/v1/order/create").handler(this::createOrder);
        router.get("/api/v1/orders/get").handler(this::getOrderHandler);
        router.post("/api/v1/orders/update/:orderId").handler(this::updateOrderHandler);
        router.delete("/api/v1/orders/cancel/:orderId").handler(this::cancelOrderHandler);

        server.requestHandler(router).listen(8080, result -> {
            if (result.succeeded()) {
                logger.info("Server started on port 8080");
            } else {
                logger.error("Failed to start server", result.cause());
            }
        });
    }

    private ResponseEntity<String> createOrder(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        request.bodyHandler(buffer -> {
            OrderDTO order = new OrderDTO();
            order.setOrderId(buffer.toJsonObject().mapTo(OrderDTO.class).getOrderId());
            try {
                orderService.createOrder(order);
            } catch (Exception e) {
                logger.error("Failed to create order", e);
                routingContext.response().setStatusCode(500).end("Internal Server Error");
            }
        });
        return ResponseEntity.ok("Order created successfully");
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
