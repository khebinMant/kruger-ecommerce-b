package krugers.microservicio.auth.authmicroservice.model;

import java.util.Date;

import lombok.Data;

@Data
public class Coupon {

    private Long id;

    private Type type;

    private Integer quantity;

    private String code;

    private Status status;

    private Date created;
}
