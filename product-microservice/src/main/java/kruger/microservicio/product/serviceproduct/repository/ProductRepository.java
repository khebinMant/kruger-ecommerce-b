package kruger.microservicio.product.serviceproduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{
    
    public List<Product> findByCategory(Category category);
    public List<Product> findByNameContaining(String name);
}
