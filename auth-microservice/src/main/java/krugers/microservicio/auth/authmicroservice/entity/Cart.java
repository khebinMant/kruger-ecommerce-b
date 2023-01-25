package krugers.microservicio.auth.authmicroservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import krugers.microservicio.auth.authmicroservice.model.Order;
import lombok.Data;
/**
 * This microservice was created by Kenan Aljaber
 */
@Entity
@Data
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name="user_id")
    private Long userId;

    @Transient
    private Order order;

    @Transient
    private User user;

    private Status status;

    public Cart() {
    }

    public Cart(Long orderId, Long userId){
        this.orderId = orderId;
        this.userId = userId;
    }

}
