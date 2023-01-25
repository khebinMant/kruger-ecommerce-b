package kruger.microservicio.product.serviceproduct.model;

import java.util.Date;

import lombok.Data;

@Data
public class Address {

    private Long id;

    private String province;

    private String city;

    private String address;

    private String street;

    private Boolean isMatriz;

    private Long userId;

    private String status;

    private Date createAt;

    private Date updateAt; 
}
