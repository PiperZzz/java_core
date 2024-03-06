package base.core;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long userId;

    private String username;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
