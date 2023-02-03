package kruger.microservicio.product.serviceproduct.service.product;

import java.util.List;

import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.entity.Product;

public interface IProductService {
    
    public List<Product> listAllProducts();
    public Product getProduct(Long id);

    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(Long id);


    public List<Product> findByCategory(Category category);
    public List<Product> findByName(String name);
    public Product updateStock(Long id, Double quantity);
    public Product updateSaleCounter(Long id, Double quantity);

}
