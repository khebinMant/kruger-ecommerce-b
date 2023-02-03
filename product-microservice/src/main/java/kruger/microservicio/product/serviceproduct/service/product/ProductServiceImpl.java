package kruger.microservicio.product.serviceproduct.service.product;

import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Service;

import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.entity.Product;
import kruger.microservicio.product.serviceproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setSalesCounter(0.0);
        product.setCreated(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());
        if(productDB == null){
            return null;
        }
        productDB.setImages(product.getImages());
        productDB.setReviews(product.getReviews());
        productDB.setName(product.getName());
        productDB.setStock(product.getStock());
        productDB.setType(product.getType());
        productDB.setBrand(product.getBrand());
        productDB.setWeight(product.getWeight());
        productDB.setProcessor(product.getProcessor());
        productDB.setStatus(product.getStatus());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());
        return productRepository.save(productDB);
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = getProduct(id);
        if(productDB ==  null){
            return null;
        }
        Double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public Product updateSaleCounter(Long id, Double quantity) {
        Product productDB = getProduct(id);
        if(productDB ==  null){
            return null;
        }
        Double salesCounter = productDB.getSalesCounter() + quantity;
        productDB.setSalesCounter(salesCounter);
        return productRepository.save(productDB);
    }
    
}
