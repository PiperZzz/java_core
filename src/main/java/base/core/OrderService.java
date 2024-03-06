package base.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Orderbook orderbook;

    public OrderDTO createOrder(OrderDTO order) {
        // 创建订单
        return order;
    }

    public OrderDTO getOrder(Long orderId) {
        // 获取订单
        return new OrderDTO();
    }

    public OrderDTO updateOrder(Long orderId, OrderDTO order) {
        // 更新订单
        return order;
    }

    public OrderDTO cancelOrder(Long orderId) {
        // 取消订单
        return new OrderDTO();
    }
}
