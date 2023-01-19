package krugers.microservicio.auth.authmicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;

import krugers.microservicio.auth.authmicroservice.dto.OrderRequest;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.repository.CartRepository;
import krugers.microservicio.auth.authmicroservice.repository.UserRepository;



@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    CartRepository cartRepository;
    
    @Override
    public Cart addCart(OrderRequest request) {
       // en caso que no hay customer con el id ingresado retorna null
       if(userRepository.findById(request.getUserId()).isPresent()){
        Cart card = new Cart(request.getOrderId(),request.getUserId());
        cartRepository.save(card);
        return card;
    }
        return null;
    }

	// @Override
	// public List<Cart> findAllByUserId(Long userId) {
		
	// 	if(userRepository.findById(userId).isPresent()) {
	// 		return userRepository.findById(userId).get().getCarts();
	// 	}
	// 	return null;
	// }

   
    

    

    
}
