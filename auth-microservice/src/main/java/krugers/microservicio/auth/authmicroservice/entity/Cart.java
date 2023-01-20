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

    //private String status;
    //PAGADO, ACEPTADO, EN VIAJE, RECIBIDO, CANCELADO, 
    //mejor ENUM

    // @JoinColumn(name = "user_id", insertable = false, updatable = false)
    // @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    // private User user;   

    public Cart(Long orderId, Long userId){
        this.orderId = orderId;
        this.userId = userId;
    }

}
