package kruger.microservicio.product.serviceproduct.service.product;

import java.util.List;


import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.entity.Product;

public interface IProductPagingService {
    public List <Product> getAllProductPaginated(Integer pageNo, Integer pageSize, String sortBy);
    public List <Product> getAllProductsByCategory(Integer pageNo, Integer pageSize, String sortBy, Category category); 
    public List <Product> getAllProductsByName(Integer pageNo, Integer pageSize, String sortBy); 
}
