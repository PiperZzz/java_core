package base.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Orderbook {
    private List<OrderDTO> orders = new ArrayList<>();

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void addOrder(OrderDTO order) {
        orders.add(order);
    }

    public void cancelOrder(Long orderId) {
        orders.removeIf(order -> order.getOrderId().equals(orderId));
    }
}
