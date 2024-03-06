package base.core;

import lombok.Data;

@Data
public class OrderDTO {
    
    private Long orderId;
    private String symbol;
    private String side; // "BUY" or "SELL"
    private double price;
    private int quantity;
    private String status; // "NEW", "FILLED", "PARTIALLY_FILLED", "CANCELLED"

    public OrderDTO(Long orderId) {
        this.orderId = orderId;
    }

    public OrderDTO(Long orderId, String symbol, String side, double price, int quantity, String status) {
        this.orderId = orderId;
        this.symbol = symbol;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public OrderDTO() {
    }
}
