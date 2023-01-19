package kruger.microservicio.product.serviceproduct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfigs {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(
                    new Info()
                    .title("Products API")
                    .description("Esta API contiene el CRUD de productos reseñas y categorias para el uso del administrador")
                    .version("V1.0")
                );
    }
}
