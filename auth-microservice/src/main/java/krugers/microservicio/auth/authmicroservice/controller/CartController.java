package krugers.microservicio.auth.authmicroservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import krugers.microservicio.auth.authmicroservice.client.order.OrderClientF;
import krugers.microservicio.auth.authmicroservice.dto.OrderRequest;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.service.cart.CartServiceImpl;
import krugers.microservicio.auth.authmicroservice.service.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Slf4j
@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    CartServiceImpl cartServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    OrderClientF orderClientF;

    @Tag(name = "Create Order in ShoppingCar")
    @Operation(description = "add  order in car", summary = "calling this endpoint will allow you to add an order to the shopping Car by passing the order data as json in the request body")
    @PostMapping("/")
    public ResponseEntity<?> addCart(@RequestBody(required = true) OrderRequest request) throws MessagingException{
        Cart user= cartServiceImpl.addCart(request);
        if(user!=null){
            log.info("Creating new cart to user ", user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not find a user with the id provided"
        ,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Cart>> getUserCarts(@PathVariable("id") Long id){

        List<Cart> carts = new ArrayList<>();

        carts = cartServiceImpl.findAllCartsByUserId(id);
        if(carts.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        carts.stream().map(cart ->{
            cart.setOrder(orderClientF.getOrder(cart.getOrderId()).getBody());
            cart.setUser(userServiceImpl.findById(cart.getUserId()));
            return cart;
        }).collect(Collectors.toList());
    log.info("Getting user carts history ", carts);
     return ResponseEntity.ok(carts);
    }

    // Obtener todas los carritos
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a list with all carts", description = "Returns a JSON response with the carts information")
    @Tag(name = "GET all carts ", description = "Retrieve information of all carts")
    @GetMapping("/")
    public ResponseEntity<List<Cart>> listCarts(){
            List<Cart> carts = new ArrayList<>();

            carts = cartServiceImpl.findCarts();
            if(carts.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            carts.stream().map(cart ->{
                cart.setOrder(orderClientF.getOrder(cart.getOrderId()).getBody());
                cart.setUser(userServiceImpl.findById(cart.getUserId()));
                return cart;
            }).collect(Collectors.toList());
        log.info("Getting all user carts", carts);
        return ResponseEntity.ok(carts);
    }

    //Obtener cart dado su id
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a cart by Id", description = "Returns a JSON response cart information")
    @Tag(name = "GET cart by Id ", description = "Retrieve information of cart by Id")
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getCart(@PathVariable(name="id") Long id){
        Cart cart = cartServiceImpl.getCart(id);
        if(cart==null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a cart with that id not found. ");
        }
        cart.setOrder(orderClientF.getOrder(cart.getId()).getBody());
        cart.setUser(userServiceImpl.findById(cart.getUserId()));
        log.info("Getting a sigle cart by Id", cart);
        return ResponseEntity.ok(cart);
    }

    
    //Obtener reporte de cart dado su id
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a cart report by Id", description = "Returns a PDF report response cart information")
    @Tag(name = "GET cart report by Id ", description = "Retrieve information PDF of cart by Id")
    @GetMapping(value="/{id}/report")
    public ResponseEntity<byte[]> getCartReport(@PathVariable(name="id") Long id) throws JRException{
       
    	JasperPrint report = cartServiceImpl.getCartReport(id);

        if(report == null){
            return new ResponseEntity<byte []>(null, null, HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers =  new HttpHeaders();
        //set PDF format
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "recibo.pdf");
        log.info("Getting a sigle report cart ", report);

        //create the report in PDF format
        return new ResponseEntity<byte []>(JasperExportManager.exportReportToPdf(report), headers, HttpStatus.OK);
    }

    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a cart with that id not found.")
	})
    @Operation(summary = "Update cart of a customer", description = "Returns a JSON response with the cart information")
    @Tag(name = "PUT update cart information", description = "Retrieve information of a created cart")
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateCart(@PathVariable(name="id") Long id, @RequestBody Cart cart) throws MessagingException{
        cart.setId(id);
        Cart cartDB = cartServiceImpl.updateCart(cart);
        if(cartDB == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a cart with that id not found. ");
        }
        cart.setOrder(orderClientF.getOrder(cart.getId()).getBody());
        cart.setUser(userServiceImpl.findById(cart.getUserId()));
        return ResponseEntity.ok(cartDB);
    }

    //Eliminar cart
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully operation"),
        @ApiResponse(responseCode = "404", description = "We cant find a cart with that id not found.")
    })
    @Operation(summary = "Delete a cart by Id", description = "Returns a message if everything is ok")
    @Tag(name = "DELETE delete a cart ", description = "Retrieve a message if everything its ok")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") long id) {

        Cart cart = cartServiceImpl.getCart(id);
        if ( null == cart ) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a cart with that id not found. ");
        }
        cartServiceImpl.deleteCart(cart.getId());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Successfully operation. ");
    }

}
