package academy.digitallab.store.serviceproduct;

import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import academy.digitallab.store.serviceproduct.entity.Category;
import academy.digitallab.store.serviceproduct.entity.Product;
import academy.digitallab.store.serviceproduct.repository.ProductRepository;
import academy.digitallab.store.serviceproduct.service.IProductService;
import academy.digitallab.store.serviceproduct.service.ProductServiceImpl;

@SpringBootTest
public class ProductServiceMockTest {
    
    @Mock 
    private ProductRepository productRepository;

    private IProductService iProductService;

    @BeforeEach 
    public void setup(){

        MockitoAnnotations.openMocks(this);
        iProductService = new ProductServiceImpl(productRepository);
        Product computer = Product.builder() 
        .name("computer")
        .category(Category.builder().id(1L).build())
        .price(Double.parseDouble("12.5"))
        .stock(Double.parseDouble("5"))
        .status("Created").build();

        Mockito.when(productRepository.findById(1L))
            .thenReturn(Optional.of(computer));

        Mockito.when(productRepository.save(computer))
            .thenReturn(computer);
    }

    @Test
    public void whenValidGetId_ThenReturnProduct(){
        Product found = iProductService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock  = iProductService.updateStock(1L,Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    }
}
