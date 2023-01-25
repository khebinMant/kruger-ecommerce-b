package kruger.microservicio.product.serviceproduct.client.fallback;

import java.util.Date;

import org.springframework.http.ResponseEntity;

import kruger.microservicio.product.serviceproduct.client.user.UserClientF;
import kruger.microservicio.product.serviceproduct.model.User;

public class UserFallback implements UserClientF{

    @Override
    public ResponseEntity<User> getUser(long id) {
        User user = new User();
        user.setCellPhone("null");
        user.setBirthDate(new Date());
        user.setCompanyId(1L);
        user.setEmail("null");
        user.setFirstName("null");
        user.setLastName("null");
        user.setImageUrl("null");
        user.setPassword("null");
        user.setRole(null);
        user.setSignDate(new Date());
        user.setVerified(true);
        return ResponseEntity.ok(user);
    }
    
}
