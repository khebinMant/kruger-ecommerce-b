package krugers.microservicio.auth.authmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import krugers.microservicio.auth.authmicroservice.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Long>{
    
}
