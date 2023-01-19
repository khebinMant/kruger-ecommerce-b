package kruger.microservicio.product.serviceproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kruger.microservicio.product.serviceproduct.entity.Review;

/**
 * This microservice was created by Kevin Mantilla
 */
public interface ReviewRepository extends JpaRepository<Review, Long>{
    
}
