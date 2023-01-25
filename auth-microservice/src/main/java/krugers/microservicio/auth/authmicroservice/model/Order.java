package krugers.microservicio.auth.authmicroservice.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Order {
    private Long id;

    private Double totalPrice;

    private Date created;

    private String status;

    private Long addressId;

    private Coupon coupon;

    private Date shipmentDate;

    private List<OrderItem> items;
}
