package kruger.microservicio.product.serviceproduct.client.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kruger.microservicio.product.serviceproduct.entity.Product;
import kruger.microservicio.product.serviceproduct.entity.Review;
import kruger.microservicio.product.serviceproduct.service.category.CategoryServiceImpl;
import kruger.microservicio.product.serviceproduct.service.product.ProductServiceImpl;


/**
 * This microservice was created by Kevin Mantilla
 */
@Component
public class ProductClient {
        

    @Autowired
    ProductServiceImpl productServiceImpl;

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    //Me sirve para llenar de categorias la base de datos cuando recién empieza
    //no es necesario poner el review en cada producto se lo puede llenar con el front end manualmente
    public void fillProductsDataBase(){

        List<Review> emptyReviews =  new ArrayList<>();

        List<Review> reviews =  new ArrayList<>();
        reviews.add(new Review(1L,5L,"Excelente calzado muy cómodo",1L,new Date()));

        //5 productos para la categoria de CALZADO
        productServiceImpl.createProduct(new Product(1L,"TENIS FORUM MID","Calzado deportivo masculino marca Adidas",154.0,100.0, "Nuevo",0.0,"https://assets.adidas.com/images/w_1880,f_auto,q_auto/6a37caa88acd4ff9b38eaf4a00f1573f_9366/HQ1508_01_standard.jpg",new Date(),categoryServiceImpl.getCategory(1L),reviews));
        productServiceImpl.createProduct(new Product(2L,"TENIS AX2R COMFORT","Calzado deportivo UNISEX marca Adidas",99.90,100.0, "Nuevo",0.0,"https://assets.adidas.com/images/w_1880,f_auto,q_auto/9d85f200e5584c75a373a87900b32e3a_9366/BB1930_01_standard.jpg", new Date(),categoryServiceImpl.getCategory(1L),emptyReviews));
        productServiceImpl.createProduct(new Product(3L,"TENIS RETROPY F2","Calzado deportivo femenino marca Adidas",124.90,100.0, "Nuevo",0.0,"https://assets.adidas.com/images/w_1880,f_auto,q_auto/41c38a56d242450fbae0ae6f01277f60_9366/GW9408_01_standard.jpg",new Date(),categoryServiceImpl.getCategory(1L),emptyReviews));
        productServiceImpl.createProduct(new Product(4L,"FORUM 84 LOW ADV","Calzado deportivo UNISEX marca Adidas",154.90,100.0, "Nuevo",0.0,"https://assets.adidas.com/images/w_1880,f_auto,q_auto/6488a40ac44e4c55b9aeae2f00399422_9366/GX9755_01_standard.jpg",new Date(),categoryServiceImpl.getCategory(1L),emptyReviews));
        productServiceImpl.createProduct(new Product(5L,"TENIS FORUM LOW","Calzado deportivo masculino marca Adidas",144.90,100.0, "Nuevo",0.0,"https://assets.adidas.com/images/w_1880,f_auto,q_auto/d9bbcdc336d84900a9c0ac5c005fed9a_9366/FY7973_01_standard.jpg",new Date(),categoryServiceImpl.getCategory(1L),emptyReviews));
        //5 productos para la categoria de ELECTRÓNICO
        productServiceImpl.createProduct(new Product(6L,"Amazon Fire Tv Stick Lite","Control por voz conectado a la red Alexa lite",78.0,100.0, "Nuevo",0.0,"https://point.com.ec/wp-content/uploads/2021/12/Amazon.jpg",new Date(),categoryServiceImpl.getCategory(2L),emptyReviews));
        productServiceImpl.createProduct(new Product(7L,"Alexa Echo Dot 4","Parlante y control por voz conectado a la red Alexa lite",95.0,100.0, "Nuevo",0.0,"https://point.com.ec/wp-content/uploads/2022/08/Alexa-hecho-dot-4.jpg",new Date(),categoryServiceImpl.getCategory(2L),emptyReviews));
        productServiceImpl.createProduct(new Product(8L,"ASUS X415 14″ Celeron N4020","Bien sea para trabajar o jugar, ASUS X415 es el laptop de entrada que ofrece un rendimiento potente y efectos visuales envolventes",399.0,100.0, "Nuevo",0.0,"https://point.com.ec/wp-content/uploads/2021/07/x415.jpg",new Date(),categoryServiceImpl.getCategory(2L),emptyReviews));
        productServiceImpl.createProduct(new Product(9L,"Play Station 5","Consola de VIDEOJUEGOS de última generación",690.99,100.0, "Nuevo",0.0,"https://point.com.ec/wp-content/uploads/2021/11/SONY-CFI1015A.jpg",new Date(),categoryServiceImpl.getCategory(2L),emptyReviews));
        productServiceImpl.createProduct(new Product(10L,"Samsung UN32T4300AP","Televisor marca Samsung de 32 pulgadas",359.99,100.0, "Nuevo",0.0,"https://point.com.ec/wp-content/uploads/2020/09/Samsung-32.png",new Date(),categoryServiceImpl.getCategory(2L),emptyReviews));
        //5 productos para la categoria de HOGAR
        productServiceImpl.createProduct(new Product(11L,"BBQ a gas","Para aquellos que no pueden elegir entre la simplicidad del gas y el sabor del carbón: bienvenidos a la parrilla híbrida Gas2Coal",800.09,100.0, "Nuevo",0.0,"https://www.todohogar.com/248925-medium_default/bbq-a-gas-3400012000btu-31-quemadores-encendido-electrico-char-broil.jpg",new Date(),categoryServiceImpl.getCategory(3L),emptyReviews));
        productServiceImpl.createProduct(new Product(12L,"Hamaca Líneas Multi Sofía","En todos los tiempos las hamacas han sido de gran utilidad para el hombre por la ventajas que ofrecen",35.99,100.0, "Nuevo",0.0,"https://www.todohogar.com/187696-medium_default/hamaca-lineas-multi-sofia-catg-2502-hamaca-lineas-sofia.jpg",new Date(),categoryServiceImpl.getCategory(3L),emptyReviews));
        productServiceImpl.createProduct(new Product(13L,"Fuente de agua eléctrica con luz","Esta fuente de agua está elaborada en poliresina, un material que ofrece una excelente resistencia a la corrosión, humedad, calor e impactos moderados y tiene una baja absorción al agua, garantizando su larga vida útil.",242.99,100.0, "Nuevo",0.0,"https://www.todohogar.com/182661-medium_default/fuente-de-agua-electrica-con-luz-led-cara-buda-3186-60.jpg",new Date(),categoryServiceImpl.getCategory(3L),emptyReviews));
        productServiceImpl.createProduct(new Product(14L,"Dispensador para comida","El mármol vuelve a estar de moda. Fácil de combinar con otros materiales y bienvenido en todos los interiores.",8.89,100.0, "Nuevo",0.0,"https://www.todohogar.com/247857-medium_default/dispensador-para-comida-de-mascota-con-antideslizante-marmoleado-savic.jpg",new Date(),categoryServiceImpl.getCategory(3L),emptyReviews));
        productServiceImpl.createProduct(new Product(15L,"Piscina redonda con bomba","¡No hay nada mejor que tener su propia piscina en el patio! La piscina Steel Pro MAX ofrece una opción ultra duradera a un precio excelente.",197.99,100.0, "Nuevo",0.0,"https://www.todohogar.com/205030-medium_default/piscina-redonda-con-bomba-filtrante-porta-bebidas-4678l-56407-bestway.jpg",new Date(),categoryServiceImpl.getCategory(3L),emptyReviews));
    }
}
