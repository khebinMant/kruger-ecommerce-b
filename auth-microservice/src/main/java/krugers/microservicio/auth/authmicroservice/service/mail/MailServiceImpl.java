package krugers.microservicio.auth.authmicroservice.service.mail;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import krugers.microservicio.auth.authmicroservice.client.coupon.CouponClient;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.model.Coupon;
import krugers.microservicio.auth.authmicroservice.model.Status;
import krugers.microservicio.auth.authmicroservice.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;
import org.springframework.core.ParameterizedTypeReference;

@Service
@Slf4j
public class MailServiceImpl implements MailService{

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    UserService userService;

    @Autowired
    TemplateEngine templateEngine;



    @Override
    public String SendMailOrderCreated(Cart cart) throws MessagingException {
        
        MimeMessage message  = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
	    RestTemplate restTemplate = new RestTemplate();

        User user = userService.findById(cart.getUserId());

        // ResponseEntity<List<Coupon>> responseEntity = 
        // restTemplate.exchange(
        //     "http://localhost:8082/api/coupons",
        //   HttpMethod.GET,
        //   null,
        //   new ParameterizedTypeReference<List<Coupon>>() {}
        // );
        
        
        // List<Coupon> coupons = responseEntity.getBody();
        
        // Optional <Coupon> couponGift = coupons.stream().filter(coupon-> coupon.getStatus() == Status.NOT_USED ).findFirst();
        
        Context context = new Context();
        context.setVariable("user", user);
        // context.setVariable("coupon", couponGift);

        String html = templateEngine.process("purchase", context);

        helper.setFrom("krugercellmag@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Orden de compra");
        helper.setText(html, true);  

        javaMailSender.send(message);
        
        log.info("Sending email: {} with html body: {}", cart, html);
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
