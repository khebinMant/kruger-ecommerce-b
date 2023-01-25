package kruger.microservicio.product.serviceproduct.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kruger.microservicio.product.serviceproduct.client.fallback.UserFallback;
import kruger.microservicio.product.serviceproduct.model.User;

@FeignClient(name="auth-microservice", path = "/api/users")
// @FeignClient(name="auth-microservice", path = "/api/users", fallback = UserFallback.class)
public interface UserClientF {
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id);
}
