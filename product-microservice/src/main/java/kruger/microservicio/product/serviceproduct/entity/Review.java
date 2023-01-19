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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This microservice was created by Kevin Mantilla
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long raiting;

    private String text;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

}
