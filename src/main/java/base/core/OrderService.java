package base.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRespository orderRespository;

    @Autowired
    private Orderbook orderbook;

    public Order createOrder(Order order) {
        // 创建订单
        return order;
    }

    public Order getOrder(Long orderId) {
        // 获取订单
        return new Order();
    }

    public Order updateOrder(Long orderId, Order order) {
        // 更新订单
        return order;
    }

    public Order cancelOrder(Long orderId) {
        // 取消订单
        return new Order();
    }
}
