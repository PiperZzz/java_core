package base.core;

import org.springframework.beans.factory.annotation.Autowired;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

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

        router.route("/api/v1/order/create").handler(this::createOrder);
    }

    private void createOrder(RoutingContext routingContext) {
        
    }
}
