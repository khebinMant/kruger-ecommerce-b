package krugers.microservicio.auth.authmicroservice.service.mail;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.Date;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.User;

import krugers.microservicio.auth.authmicroservice.service.user.UserService;
import lombok.extern.slf4j.Slf4j;

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

        User user = userService.findById(cart.getUserId());
        Context context = new Context();
        context.setVariable("user", user);

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
    public String SendMailOrderCanceled(Cart cart) throws MessagingException {
                
        MimeMessage message  = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        User user = userService.findById(cart.getUserId());
        Context context = new Context();
        context.setVariable("user", user);

        String html = templateEngine.process("cancel", context);

        helper.setFrom("krugercellmag@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Cancelación de compra");
        helper.setText(html, true);  
    

        javaMailSender.send(message);
        log.info("Sending email: {} with html body: {}", cart, html);
        return "Email Sended";
    }

    @Override
    public String SendMailOrderInTravel(Cart cart) throws MessagingException {
        MimeMessage message  = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        User user = userService.findById(cart.getUserId());
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("cart", cart);
        context.setVariable("today", new Date());

        
        String html = templateEngine.process("order", context);

        helper.setFrom("krugercellmag@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Tu compra esta en camino");
        helper.setText(html, true);  
    

        javaMailSender.send(message);
        log.info("Sending email: {} with html body: {}", cart, html);
        return "Email Sended";
    }

    @Override
    public String SendMailOrderInReceived(Cart cart) throws MessagingException {
        MimeMessage message  = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        User user = userService.findById(cart.getUserId());
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("cart", cart);
        context.setVariable("today", new Date());
        
        String html = templateEngine.process("arrived", context);

        helper.setFrom("krugercellmag@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Sano y salvo!");
        helper.setText(html, true);  
    

        javaMailSender.send(message);
        log.info("Sending email: {} with html body: {}", cart, html);
        return "Email Sended";
    }
    
}
