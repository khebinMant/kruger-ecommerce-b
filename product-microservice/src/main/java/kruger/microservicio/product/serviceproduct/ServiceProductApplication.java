package kruger.microservicio.product.serviceproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

import kruger.microservicio.product.serviceproduct.client.category.CategoryClient;
import kruger.microservicio.product.serviceproduct.client.product.ProductClient;
/**
 * This microservice was created by Kevin Mantilla
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class ServiceProductApplication implements CommandLineRunner{

	@Autowired
	CategoryClient categoryClient;
	
	@Autowired
	ProductClient productClient;


	@Autowired
	public ConfigurableApplicationContext context;

	public static void main(final String[] args) {
		SpringApplication application = new SpringApplication(ServiceProductApplication.class);
		application.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Creo categorias
		categoryClient.fillCategoriesDataBase();
		//Creo productos y adjunto su respectiva categoria
		productClient.fillProductsDataBase();
	}

}
