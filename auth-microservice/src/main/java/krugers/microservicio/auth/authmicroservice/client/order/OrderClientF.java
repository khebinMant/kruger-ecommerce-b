package krugers.microservicio.auth.authmicroservice.client.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import krugers.microservicio.auth.authmicroservice.client.fallback.OrderFallback;
import krugers.microservicio.auth.authmicroservice.model.Order;

@FeignClient(name="order-microservice", path = "/api/orders", fallback = OrderFallback.class)
public interface OrderClientF {
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id);
}
