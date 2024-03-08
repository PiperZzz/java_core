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
        Order order = orderRepository.findByOrderId(orderId);
        if (order != null) {
            return new OrderDTO(order.getOrderId());
        } else {
            return new OrderDTO();
        }
    }

    public OrderDTO updateOrder(@NotNull Long orderId, @NotBlank OrderDTO order) {
        Order existingOrder = orderRepository.findByOrderId(orderId);
        if (existingOrder != null) {
            existingOrder.setOrderId(order.getOrderId());
            try {
                orderRepository.save(existingOrder);
                return new OrderDTO(existingOrder.getOrderId());
            } catch (Exception e) {
                return new OrderDTO();
            }
        } else {
            return new OrderDTO();
        }
    }

    public void cancelOrder(Long orderId) {
        // 取消订单
    }
}
