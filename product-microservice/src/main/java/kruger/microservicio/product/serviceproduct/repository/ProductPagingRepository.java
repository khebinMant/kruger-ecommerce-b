package kruger.microservicio.product.serviceproduct.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.entity.Product;

public interface ProductPagingRepository extends PagingAndSortingRepository<Product, Long>{
    Page <Product> findByCategory(Category category, Pageable pageable);
}
