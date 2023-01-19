package kruger.microservicio.product.serviceproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kruger.microservicio.product.serviceproduct.entity.Category;

/**
 * This microservice was created by Kevin Mantilla
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
