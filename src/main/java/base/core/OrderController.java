package base.core;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/get")
    public ResponseEntity<Order> getOrder(@RequestParam Long orderId) {
        Order newOrder = orderService.getOrder(orderId);
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestParam Long orderId, @RequestBody Order order) {
        Order newOrder = orderService.updateOrder(orderId, order);
        return ResponseEntity.ok(newOrder);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<Order> cancelOrder(@RequestParam Long orderId) {
        Order newOrder = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(newOrder);
    }
}
