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
        ("Tu orden se ha procesado con éxito gracias por confiar en nosotros, si tienes alguna pregunta no dudes en responder");
        javaMailSender.send(email);

        return "Email Sended";
    }

    @Override
    public String SendMailOrderCanceled(Cart cart) {
                
        SimpleMailMessage email = new SimpleMailMessage();
        User user = userService.findById(cart.getUserId());
        email.setTo(user.getEmail());
        email.setFrom("krugercellmag@gmail.com");

        email.setSubject("Cancelación de la orden");
        email.setText
        ("Tu orden ha sido cancelada, esperamos haya tenido una buena experiencia usando nuestra plataforma, la devolución del pago se realizara dentro de las próximas 24 horas");
        javaMailSender.send(email);

        return "Email Sended";
    }

    @Override
    public String SendMailOrderInTravel(Cart cart) {
        SimpleMailMessage email = new SimpleMailMessage();
        User user = userService.findById(cart.getUserId());
        email.setTo(user.getEmail());
        email.setFrom("krugercellmag@gmail.com");

        email.setSubject("Tu pedido esta en viaje");
        email.setText
        ("Buen dia " + user.getFirstName() + " " +  user.getLastName() + "este correo es para informarle que su pedido ya esta en camino, si tiene alguna preguntas no dudes en responder");
        javaMailSender.send(email);

        return "Email Sended";
    }

    @Override
    public String SendMailOrderInReceived(Cart cart) {
        SimpleMailMessage email = new SimpleMailMessage();
        User user = userService.findById(cart.getUserId());
        email.setTo(user.getEmail());
        email.setFrom("krugercellmag@gmail.com");

        email.setSubject("Sano y ssalvo");
        email.setText
        ("Buen dia " + user.getFirstName() + user.getLastName() + "este correo es para copnfirmar que su pedido ha llegado a al dirección de envío, si tiene alguna preguntas no dudes en responder");
        javaMailSender.send(email);
        return "Email Sended";
    }
    
}
