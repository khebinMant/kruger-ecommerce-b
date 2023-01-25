package krugers.microservicio.auth.authmicroservice.model;

import java.util.Date;

import lombok.Data;

@Data
public class OrderItem {

    private Long id;

    private Double quantity;

    private Double  price;

    private Long productId;

    private Double subTotal;

    private Product product;

    private Date created;
}
