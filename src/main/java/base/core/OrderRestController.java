package base.core;

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

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO order) {
        OrderDTO newOrder = orderService.createOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/get")
    public ResponseEntity<OrderDTO> getOrder(@RequestParam("orderId") Long orderId, @RequestParam(value = "userId", required = false) Long userId) {
        OrderDTO newOrder = orderService.getOrder(orderId);
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO order) {
        OrderDTO newOrder = orderService.updateOrder(orderId, order);
        return ResponseEntity.ok(newOrder);
    }

    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId) {
        OrderDTO newOrder = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(newOrder);
    }
}
