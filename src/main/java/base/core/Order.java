package base.core;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "Orders") // "Order" is a reserved keyword in SQL
@Data
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long orderId;

    private String symbol;
    private String side; // "BUY" or "SELL"
    private double price;
    private int quantity;
    private String status; // "NEW", "FILLED", "PARTIALLY_FILLED", "CANCELLED"
    
    public Order(String symbol, String side, double price, int quantity, String status) {
        this.symbol = symbol;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", symbol='" + symbol + '\'' +
                ", side='" + side + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }

}