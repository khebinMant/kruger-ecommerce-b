package krugers.microservicio.auth.authmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/api/mails")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/test")
    public ResponseEntity<?> sendEmail(){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("alt.np-afn6oun@yopmail.com");
        email.setFrom("krugercellmag@gmail.com");
        email.setSubject("Mensaje de prueba de envio de correos electrónicos");
        email.setText("No responder esta es una una prueba");
        javaMailSender.send(email);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
