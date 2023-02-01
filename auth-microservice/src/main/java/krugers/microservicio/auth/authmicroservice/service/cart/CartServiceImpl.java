package krugers.microservicio.auth.authmicroservice.service.cart;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.netflix.discovery.converters.Auto;

import krugers.microservicio.auth.authmicroservice.client.order.OrderClientF;
import krugers.microservicio.auth.authmicroservice.client.product.ProductClient;
import krugers.microservicio.auth.authmicroservice.dto.OrderRequest;
import krugers.microservicio.auth.authmicroservice.entity.Address;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.Status;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.model.Order;
import krugers.microservicio.auth.authmicroservice.model.OrderItem;
import krugers.microservicio.auth.authmicroservice.repository.CartRepository;
import krugers.microservicio.auth.authmicroservice.repository.UserRepository;
import krugers.microservicio.auth.authmicroservice.service.address.AddressServiceImpl;
import krugers.microservicio.auth.authmicroservice.service.mail.MailService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



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

    @Autowired
    AddressServiceImpl addressServiceImpl;
    
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

    @Override
    public JasperPrint getCartReport(Long id) {

        Map<String, Object> reportParameters = new HashMap<String, Object>();
        Address shipmentAddress = new Address();
        Cart cart = getCart(id);
        if (cart == null){
            return null;
        }
        
        cart.setOrder(orderClientF.getOrder(cart.getId()).getBody());
        cart.setUser(userRepository.findById(cart.getUserId()).orElse(null));
        if(cart.getOrder().getAddressId()!=null){
            shipmentAddress = addressServiceImpl.getAddress(cart.getOrder().getAddressId());
        }

        // reportParameters.put("cartId", cart.getId());
        reportParameters.put("firstName", cart.getUser().getFirstName());
        reportParameters.put("lastName", cart.getUser().getLastName());
        reportParameters.put("cellPhone", cart.getUser().getCellPhone());
        reportParameters.put("province", shipmentAddress.getProvince());
        reportParameters.put("city", shipmentAddress.getCity());
        reportParameters.put("street", shipmentAddress.getStreet());
        reportParameters.put("address", shipmentAddress.getAddress());
        reportParameters.put("subTotalG", cart.getOrder().getSubTotal());
        reportParameters.put("totalG", cart.getOrder().getTotalPrice());
        //Order Items
        List<Map<String, Object>> dataList = new ArrayList<>();
        
        for(OrderItem item : cart.getOrder().getItems()){
            Map<String, Object> data = new HashMap<>();
            data.put("name", item.getProduct().getName());
            data.put("price", item.getProduct().getPrice());
            data.put("quantity", item.getQuantity());
            data.put("subTotal", item.getSubTotal());
            dataList.add(data);
        }
        reportParameters.put("orderItems", new JRBeanCollectionDataSource(dataList));

        JasperPrint reportJasperPrint = null;
        try{
            reportJasperPrint = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(
                    ResourceUtils.getFile("classpath:jrxml/ReciboCompra.jrxml")
                    .getAbsolutePath())
            , reportParameters
            , new JREmptyDataSource());
        }catch(FileNotFoundException | JRException e){
            e.printStackTrace();
        }

        return reportJasperPrint;
    }
    

}
