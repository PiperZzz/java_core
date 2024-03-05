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
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO order) {
        OrderDTO newOrder = orderService.createOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/get")
    public ResponseEntity<OrderDTO> getOrder(@RequestParam Long orderId) {
        OrderDTO newOrder = orderService.getOrder(orderId);
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDTO> updateOrder(@RequestParam Long orderId, @RequestBody OrderDTO order) {
        OrderDTO newOrder = orderService.updateOrder(orderId, order);
        return ResponseEntity.ok(newOrder);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@RequestParam Long orderId) {
        OrderDTO newOrder = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(newOrder);
    }
}
