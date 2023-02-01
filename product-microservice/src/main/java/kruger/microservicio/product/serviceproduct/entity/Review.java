package kruger.microservicio.product.serviceproduct.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import kruger.microservicio.product.serviceproduct.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long raiting;

    @Column(columnDefinition="TEXT")
    private String text;

    @Column(name="user_id")
    private Long userId;

    @Transient
    private User user;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Review(Long id, Long raiting, String text, Long userId, Long productId, Date created) {
        this.id = id;
        this.raiting = raiting;
        this.text = text;
        this.userId = userId;
        this.productId = productId;
        this.created = created;
    }
}
