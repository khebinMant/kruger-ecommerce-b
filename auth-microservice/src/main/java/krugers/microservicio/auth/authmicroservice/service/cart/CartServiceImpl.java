package krugers.microservicio.auth.authmicroservice.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;

import krugers.microservicio.auth.authmicroservice.dto.OrderRequest;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.Status;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.repository.CartRepository;
import krugers.microservicio.auth.authmicroservice.repository.UserRepository;
import krugers.microservicio.auth.authmicroservice.service.mail.MailService;



@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    CartRepository cartRepository;

    @Autowired
    MailService mailService;
    
    @Override
    public Cart addCart(OrderRequest request) {
       // en caso que no hay customer con el id ingresado retorna null
       if(userRepository.findById(request.getUserId()).isPresent()){
        Cart cart = new Cart(request.getOrderId(),request.getUserId());
        cart.setStatus(Status.PAID);
        cartRepository.save(cart);
        mailService.SendMailOrderCreated(cart);
        return cart;
    }
        return null;
    }

    @Override
    public List<Cart> findAllCartsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public List<Cart> findCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart updateCart(Cart cart) {
        Cart cartDB = getCart(cart.getId());
        if(cartDB == null){
            return null;
        }

        cartDB.setStatus(cart.getStatus());
       
        return cartRepository.save(cartDB);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElse(null);
    }
    

}
