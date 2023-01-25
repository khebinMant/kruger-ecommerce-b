package krugers.microservicio.auth.authmicroservice.model;

import java.util.Date;

import lombok.Data;
/**
 * This microservice was created by Kevin 
 */
@Data
public class Product {

    private Long id;

    private String name;

    private String description;
    
    private Double price;

    private Double stock;

    private String status;

    private Double salesCounter;

    private String photoUrl;

    private Date created;

    private Category category;    
}
