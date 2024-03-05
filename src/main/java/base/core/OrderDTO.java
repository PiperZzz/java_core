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
}
