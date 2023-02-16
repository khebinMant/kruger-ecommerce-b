package krugers.microservicio.auth.authmicroservice.service.mail;

import jakarta.mail.MessagingException;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.User;

public interface MailService {
    public String SendMailOrderCreated(Cart cart) throws MessagingException;
    public String SendMailOrderCanceled(Cart cart) throws MessagingException;
    public String SendMailOrderInTravel(Cart cart) throws MessagingException;
    public String SendMailOrderInReceived(Cart cart) throws MessagingException;
    
    //password recovery
    public String senRecoveryCode(String email,User user,String recoveryCode) throws MessagingException;
}
