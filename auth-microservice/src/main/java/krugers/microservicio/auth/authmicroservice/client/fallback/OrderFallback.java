package krugers.microservicio.auth.authmicroservice.client.fallback;

import java.util.Date;

import org.springframework.http.ResponseEntity;

import krugers.microservicio.auth.authmicroservice.client.order.OrderClientF;
import krugers.microservicio.auth.authmicroservice.model.Order;

public class OrderFallback implements OrderClientF{

    @Override
    public ResponseEntity<Order> getOrder(Long id) {
        Order orderFall = new Order();
        orderFall.setAddressId(null);
        orderFall.setCoupon(null);
        orderFall.setCreated(new Date());
        orderFall.setItems(null);
        orderFall.setShipmentDate(new Date());
        orderFall.setStatus(null);
        orderFall.setTotalPrice(null);
        return ResponseEntity.ok(orderFall);
    }
    
}
