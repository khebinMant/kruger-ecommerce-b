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
        //SI ES CANCELED debo de regresar al stock los productos
        // y restar el contador de ventas en el producto
        // ENVIAR CORREO AL CLIENTE DICIENDO QUE SU ORDEN FUE CANCELADA
        // Y QUE SE HARA UN REMBOLSO DE SU DINERO EN LAS 24 HORAS

        //SI ES EN IN_TRAVEL 
        // SE ENVIA UN MENSAJE AL CLIENTE AVISANDO QUE EL PEDIDO ESTA EN VIAJE

        //SI ES RECIBIDO
        //SE ENVÍA UN MENSAJE POR CORREO DE QUE NOS ALEGRAMOS QUE SU 
        // PEDIDO HAYA LLEGADO A SALVO
        
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
