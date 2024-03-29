package base.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO order) {
        try {
            orderService.createOrder(order);
            return ResponseEntity.ok("Order created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<OrderDTO> getOrder(@RequestParam("orderId") Long orderId, @RequestParam(value = "userId", required = false) Long userId) {
        OrderDTO newOrder = orderService.getOrder(orderId);
        return ResponseEntity.ok(newOrder);
    }

    @PostMapping("/update/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO order) {
        try {
            OrderDTO newOrder = orderService.updateOrder(orderId, order);
            logger.info("Order updated: {}", newOrder);
            return ResponseEntity.ok(newOrder);
        } catch (Exception e) {
            logger.error("updateOrder error: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order cancelled");
    }
}
