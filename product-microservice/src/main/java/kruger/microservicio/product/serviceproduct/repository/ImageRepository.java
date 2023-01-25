package kruger.microservicio.product.serviceproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kruger.microservicio.product.serviceproduct.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
}
