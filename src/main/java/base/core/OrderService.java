package base.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Orderbook orderbook;

    @Transactional
    public void createOrder(@NotNull OrderDTO order) {
        Order newOrder = new Order();
        newOrder.setOrderId(order.getOrderId());
        orderRepository.save(newOrder);
    }

    public OrderDTO getOrder(@NotBlank Long orderId) {
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
