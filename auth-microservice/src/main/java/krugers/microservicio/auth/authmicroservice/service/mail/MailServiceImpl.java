package krugers.microservicio.auth.authmicroservice.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.service.user.UserService;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    UserService userService;

    @Override
    public String SendMailOrderCreated(Cart cart) {
        
        SimpleMailMessage email = new SimpleMailMessage();
        User user = userService.findById(cart.getUserId());
        email.setTo(user.getEmail());
        email.setFrom("krugercellmag@gmail.com");

        email.setSubject("Orden de compra");
        email.setText
        ("Tu orden se ha procesado con éxito gracias por confiar en nosotros si tienes, alguna pregunta no dudes en responder");
        javaMailSender.send(email);

        return "Email Sended";
    }
    
}
