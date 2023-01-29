package krugers.microservicio.auth.authmicroservice.service.mail;

import krugers.microservicio.auth.authmicroservice.entity.Cart;

public interface MailService {
    public String SendMailOrderCreated(Cart cart);
}
