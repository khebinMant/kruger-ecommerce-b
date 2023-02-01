package krugers.microservicio.auth.authmicroservice.service.cart;

import java.util.List;

import krugers.microservicio.auth.authmicroservice.dto.OrderRequest;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import net.sf.jasperreports.engine.JasperPrint;

public interface CartService {
	/**
     * este metodo va a agregar una nueva carta con un nuevo orderId 
     * @param request es el objeto que carga los orderId y customerId
     * @return el customer que le fue agregado la carta
     */
    public Cart addCart (OrderRequest request);

    /**
     * este metodo busca todas las cartas que tienen el customerId equal al parametro id
     * @param id es el id del customer que estamos buscando
     * @return list de todas las cartas que estan el base de datos
     */
    public List<Cart> findAllCartsByUserId(Long userId);

    public List<Cart> findCarts();
    public Cart updateCart(Cart cart); // cambiar el STATUS  del carrito
    public void deleteCart(Long id);
    public Cart getCart(Long id);
    public JasperPrint getCartReport(Long id);
}
