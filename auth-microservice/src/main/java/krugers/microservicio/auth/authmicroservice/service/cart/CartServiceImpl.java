package krugers.microservicio.auth.authmicroservice.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;

import krugers.microservicio.auth.authmicroservice.client.order.OrderClientF;
import krugers.microservicio.auth.authmicroservice.client.product.ProductClient;
import krugers.microservicio.auth.authmicroservice.dto.OrderRequest;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.Status;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.model.Order;
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

    @Autowired
    OrderClientF orderClientF;

    @Autowired
    ProductClient productClient;
    
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
        //CUANDO SE CAMBIA DE ESTADO A CANCELED
        if(cart.getStatus() == Status.CANCELED){
            Order cartOrder = orderClientF.getOrder(cart.getId()).getBody();
            //Actualizar el stock del producto
            cartOrder.getItems().forEach(orderItem ->{
                productClient.updateStockProduct(orderItem.getProductId(), orderItem.getQuantity());
            });
            //Actualizar el contador de ventas en el producto
            cartOrder.getItems().forEach( orderItem ->{
                productClient.updateSaleCounter(orderItem.getProductId(), orderItem.getQuantity()*-1);
            });
            //Envío un correo al cliente confirmando que su orden ha sido cancelada
            mailService.SendMailOrderCanceled(cart);
        }
        //CUANDO SE CAMBIA DE ESTADO A IN_TRAVEL
        if(cart.getStatus() == Status.IN_TRAVEL){
            mailService.SendMailOrderInTravel(cart);
        }
        //CUANDO SE CAMBIA DE ESTADO A RECIBIDO
        if(cart.getStatus() == Status.RECEIVED){
            mailService.SendMailOrderInReceived(cart);
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
