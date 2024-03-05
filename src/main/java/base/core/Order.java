package base.core;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "Orders") // "Order" is a reserved keyword in SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String symbol;
    private String side; // "BUY" or "SELL"
    private double price;
    private int quantity;
    private String status; // "NEW", "FILLED", "PARTIALLY_FILLED", "CANCELLED"
}