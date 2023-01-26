package kruger.microservicio.product.serviceproduct.seeders.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.infix.lang.infix.antlr.EventFilterParser.null_predicate_return;

import kruger.microservicio.product.serviceproduct.entity.Image;
import kruger.microservicio.product.serviceproduct.entity.Product;
import kruger.microservicio.product.serviceproduct.entity.Status;
import kruger.microservicio.product.serviceproduct.entity.Type;
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

    // No es necesario poner el review en cada producto se lo puede llenar con el
    // front end manualmente
    public List<Image> fillImagesToP1 (){
        List<Image> images = new ArrayList<>();
        images.add(new Image(1L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/combo-apple-iphone-14-128gb-apple-20w-power-adapter/negro/zoom/01-combo-apple-iphone-14-128gb-apple-20w-power-adapter-negro-front.png", 1L,new Date()));
        images.add(new Image(2L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/combo-apple-iphone-14-128gb-apple-20w-power-adapter/negro/zoom/03-combo-apple-iphone-14-128gb-apple-20w-power-adapter-negro-back.png", 1L,new Date()));
        images.add(new Image(3L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/combo-apple-iphone-14-128gb-apple-20w-power-adapter/negro/zoom/04-combo-apple-iphone-14-128gb-apple-20w-power-adapter-negro-other.png", 1L,new Date()));
        images.add(new Image(4L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/combo-apple-iphone-14-128gb-apple-20w-power-adapter/negro/zoom/02-combo-apple-iphone-14-128gb-apple-20w-power-adapter-negro-side.png", 1L,new Date()));
        
        return images;
    }

    public List<Image> fillImagesToP2(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(5L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/combo-apple-iphone-13-128gb-apple-20w-power-adapter/negro/zoom/02-combo-apple-iphone-13-128gb-apple-20w-power-adapter-negro-side.png", 2L,new Date()));
        images.add(new Image(6L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/combo-apple-iphone-13-128gb-apple-20w-power-adapter/negro/zoom/04-combo-apple-iphone-13-128gb-apple-20w-power-adapter-negro-other.png", 2L,new Date()));
        images.add(new Image(7L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/combo-apple-iphone-13-128gb-apple-20w-power-adapter/negro/zoom/03-combo-apple-iphone-13-128gb-apple-20w-power-adapter-negro-back.png", 2L,new Date()));
        images.add(new Image(8L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/combo-apple-iphone-13-128gb-apple-20w-power-adapter/negro/zoom/01-combo-apple-iphone-13-128gb-apple-20w-power-adapter-negro-front.png", 2L,new Date()));

        return images;
    }

    public List<Image> fillImagesToP3(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(9L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/samsung-galaxy-s22-ultra-buds-2-pro-white-samsung-chr-25w/verde/zoom/04-samsung-galaxy-s22-ultra-buds-2-pro-white-samsung-chr-25w-verde-other.png", 3L,new Date()));
        images.add(new Image(10L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/samsung-galaxy-s22-ultra-buds-2-pro-white-samsung-chr-25w/verde/zoom/03-samsung-galaxy-s22-ultra-buds-2-pro-white-samsung-chr-25w-verde-back.png", 3L,new Date()));
        images.add(new Image(11L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/samsung-galaxy-s22-ultra-buds-2-pro-white-samsung-chr-25w/verde/zoom/02-samsung-galaxy-s22-ultra-buds-2-pro-white-samsung-chr-25w-verde-side.png", 3L,new Date()));
        images.add(new Image(12L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/samsung-galaxy-s22-ultra-buds-2-pro-white-samsung-chr-25w/verde/zoom/01-samsung-galaxy-s22-ultra-buds-2-pro-white-samsung-chr-25w-verde-front.png", 3L,new Date()));
        
        return images;
    }

    public List<Image> fillImagesToP4(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(13L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/iphone-14-pro-max-1tb/negro/zoom/01-iphone-14-pro-max-1tb-negro-front.png", 4L,new Date()));
        images.add(new Image(14L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/iphone-14-pro-max-1tb/negro/zoom/03-iphone-14-pro-max-1tb-negro-back.png", 4L,new Date()));
        images.add(new Image(15L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/iphone-14-pro-max-1tb/negro/zoom/04-iphone-14-pro-max-1tb-negro-other.png", 4L,new Date()));
        images.add(new Image(16L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/iphone-14-pro-max-1tb/negro/zoom/02-iphone-14-pro-max-1tb-negro-side.png", 4L,new Date()));

        return images;
    }

    public List<Image> fillImagesToP5(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(17L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/honor-70-256gb/verde/zoom/04-honor-70-256gb-verde-other.png", 5L,new Date()));
        images.add(new Image(18L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/honor-70-256gb/verde/zoom/03-honor-70-256gb-verde-back.png", 5L,new Date()));
        images.add(new Image(19L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/honor-70-256gb/verde/zoom/05-honor-70-256gb-verde-down.png", 5L,new Date()));
        images.add(new Image(20L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/honor-70-256gb/verde/zoom/02-honor-70-256gb-verde-side.png", 5L,new Date()));

        return images;
    }

    public List<Image> fillImagesToP6(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(21L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/xiaomi-redmi-note-11-pro-128gb/gris/zoom/01-xiaomi-redmi-note-11-pro-128gb-gris-front.png", 6L,new Date()));
        images.add(new Image(22L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/xiaomi-redmi-note-11-pro-128gb/gris/zoom/03-xiaomi-redmi-note-11-pro-128gb-gris-back.png", 6L,new Date()));
        images.add(new Image(23L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/xiaomi-redmi-note-11-pro-128gb/gris/zoom/03-xiaomi-redmi-note-11-pro-128gb-gris-back.png", 6L,new Date()));
        images.add(new Image(24L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/xiaomi-redmi-note-11-pro-128gb/gris/zoom/02-xiaomi-redmi-note-11-pro-128gb-gris-side.png", 6L,new Date()));

        return images;
    }

    public List<Image> fillImagesToP7(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(25L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/samsung-galaxy-s21-fe/gris/zoom/01-samsung-sin-texto-galaxy-s21-fe-gris-front.png", 7L,new Date()));
        images.add(new Image(26L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/samsung-galaxy-s21-fe/gris/zoom/03-samsung-galaxy-s21-fe-gris-back.png", 7L,new Date()));
        images.add(new Image(27L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/samsung-galaxy-s21-fe/gris/zoom/02-samsung-galaxy-s21-fe-gris-side.png", 7L,new Date()));

        return images;
    }

    public List<Image> fillImagesToP8(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(28L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/iphone-11-64gb/negro/zoom/01-iphone-11-64gb-negro-front.png", 8L,new Date()));
        images.add(new Image(29L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/iphone-11-64gb/negro/zoom/03-iphone-11-64gb-negro-back.png", 8L,new Date()));
        images.add(new Image(30L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/iphone-11-64gb/negro/zoom/02-iphone-11-64gb-negro-side.png", 8L,new Date()));

        return images;
    }
    
    public List<Image> fillImagesToP9(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(31L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/tcl-6125f1/azul/zoom/01-tcl-6125f1-azul-front.png", 9L,new Date()));
        images.add(new Image(32L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/tcl-6125f1/azul/zoom/02-tcl-6125f1-azul-side.png", 9L,new Date()));
        images.add(new Image(33L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/tcl-6125f1/azul/zoom/03-tcl-6125f1-azul-back.png", 9L,new Date()));

        return images;
    }

    public List<Image> fillImagesToP10(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(35L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/xiaomi-11-lite-ne/rosa/zoom/01-xiaomi-11-lite-ne-rosa-front.png", 10L,new Date()));
        images.add(new Image(36L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/xiaomi-11-lite-ne/rosa/zoom/03-xiaomi-11-lite-ne-rosa-back.png", 10L,new Date()));
        images.add(new Image(37L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/xiaomi-11-lite-ne/rosa/zoom/02-xiaomi-11-lite-ne-rosa-side.png", 10L,new Date()));

        return images;
    }
    public List<Image> fillImagesToP11(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(38L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/nokia-g10-64-gb/morado/zoom/01-nokia-g10-64-gb-morado-front.png", 11L,new Date()));
        images.add(new Image(39L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/nokia-g10-64-gb/morado/zoom/03-nokia-g10-64-gb-morado-back.png", 11L,new Date()));
        images.add(new Image(40L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/nokia-g10-64-gb/morado/zoom/02-nokia-g10-64-gb-morado-side.png", 11L,new Date()));

        return images;
    }

    public List<Image> fillImagesToP12(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(41L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/samsung-a03-core/azul/zoom/01-samsung-a03-core-azul-front.png", 11L,new Date()));
        images.add(new Image(42L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/samsung-a03-core/azul/zoom/01-samsung-a03-core-azul-front.png", 11L,new Date()));
        images.add(new Image(43L, null, "https://catalogo.claro.com.ec/uploads/imgs/productos/samsung-a03-core/azul/zoom/02-samsung-a03-core-azul-side.png", 11L,new Date()));

        return images;
    }



    public void fillProductsDataBase() {

        List<Review> emptyReviews = new ArrayList<>();

        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1L, 5L, "Excelente teléfono muy avanzado", 1L, 1L, new Date()));
        reviews.add(new Review(2L, 3L, "Me encanta aunque la camará puede mejorar", 2L, 1L, new Date()));
        reviews.add(new Review(3L, 4L, "Me encanta es el mejor teléfono de todos", 3L, 1L, new Date()));
       

        // 4 productos para la categoria de Gama ALTA
        productServiceImpl.createProduct(new Product(
            1L, 
            "IPHONE 14 (128 GB)",
            "Frente de Ceramic Shield Diseño de aluminio con parte posterior de vidrio", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            1162.62, 
            100.0,
            Status.POPULAR, 
            Type.PRODUCT, 
            "Apple", 
            173.0, 
            "Chip A15 Bionic", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(1L),
            reviews, fillImagesToP1()
        ));
        productServiceImpl.createProduct(new Product(
            2L, 
            "IPHONE 13 (128 GB)",
            "Frente de Ceramic Shield Diseño de aluminio con parte posterior de vidrio",
            "https://www.youtube.com/embed/TBTgQbjRsqg", 
            1009.90, 
            100.0,
            Status.NOT_POPULAR, 
            Type.PRODUCT, 
            "Apple", 
            173.0, 
            "Chip A15 Bionic", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(1L),
            emptyReviews, fillImagesToP2()
        ));
        productServiceImpl.createProduct(new Product(
            3L, 
            "GALAXY S22 ULTRA (512GB)",
            " Samsung cuenta con una de las mejores opciones del mercado actualmente: el Samsung Galaxy S22 Ultra. Un móvil de gran calidad y con un excelente diseño.", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            1566.88, 
            100.0,
            Status.POPULAR, 
            Type.PRODUCT, 
            "Samsung", 
            227.0, 
            "Exynos 2200 a 2,8GHz GPU AMD", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(1L),
            emptyReviews, fillImagesToP3()
        ));

        productServiceImpl.createProduct(new Product(
            4L, 
            "IPHONE 14 PRO MAX (1TB)",
            "Frente de Ceramic Shield Diseño de acero inoxidable con parte posterior de vidrio mate texturizado", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            2368.42, 
            100.0,
            Status.NOT_POPULAR, 
            Type.PRODUCT, 
            "Apple", 
            206.0, 
            "Chip A16 Bionic", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(1L),
            emptyReviews, fillImagesToP4()
        ));

        // 4 productos para la categoria de Gama MEDIA
        productServiceImpl.createProduct(new Product(
            5L, 
            "HONOR 70 (256GB)",
            "El Honor 70 es ergonómico y cómodo pese a ser grande, además de ser relativamente ligero para su tamaño", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            742.00, 
            100.0,
            Status.POPULAR, 
            Type.PRODUCT, 
            "Huawei", 
            178.0, 
            "Snapdragon 778G Plus 5G", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(2L),
            emptyReviews, fillImagesToP5()
        ));

        productServiceImpl.createProduct(new Product(
            6L, 
            "XIAOMI REDMI NOTE 11 PRO (128GB)",
            "l Redmi Note 11 Pro cuenta con una pantalla AMOLED Full HD+ de 6.67 pulgadas con tasa de refresco de 120Hz, procesador  MediaTek Helio G96 2.05GHz con hasta 8GB de RAM y hasta 128GB de almacenamiento, cámara dorsal triple de 108MP con lentes ultrawide de 8MP y macro de 2MP, cámara selfie de 16MP, batería de 5000 mAh con carga rápida de 67W, lector de huellas en su lateral, resistencia al agua IP53, parlantes stereo, y corre MIUI 13 basado en Android 11.", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            550.00, 
            100.0,
            Status.NOT_POPULAR, 
            Type.PRODUCT, 
            "Xiaomi", 
            193.0, 
            "MediaTek Helio G96 2.05GHz", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(2L),
            emptyReviews, fillImagesToP6()
        ));

        productServiceImpl.createProduct(new Product(
            7L, 
            "GALAXY S21 FE (128GB)",
            "Al igual que sus hermanos de la serie S21, el Samsung Galaxy S21 FE tiene una cámara triple, en la que se incluye un Teleobjetivo y un lente principal y Ultra Gran Angular de 12 MP. La app de la cámara tiene muchas funciones como filtros de belleza, modo retrato y HDR, aunque esta abundancia de herramientas satura y se torna a veces desordenado. De todas maneras, la cámara funciona bien, se resaltan los detalles y tienen buen contraste.", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            952.00, 
            100.0,
            Status.NOT_POPULAR, 
            Type.PRODUCT, 
            "Samsumg", 
            177.0, 
            "Exynos 2100", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(2L),
            emptyReviews, fillImagesToP7()
        ));

        productServiceImpl.createProduct(new Product(
            8L, 
            "IPHONE 11 (64GB)",
            "El Apple iPhone 11 es el sucesor del iPhone Xr para el 2019. Este año el iPhone 11 llega con una pantalla de 6.1 pulgadas con resolución Liquid Retina y potenciado por un procesador Apple A13 Bionic con 64GB, 128GB o 256GB de almacenamiento interno.", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            644.00, 
            100.0,
            Status.POPULAR, 
            Type.PRODUCT, 
            "Iphone", 
            194.0, 
            "Chip A13 Bionic", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(2L),
            emptyReviews, fillImagesToP8()
        ));

        // 4 productos para la categoria de Gama BAJA
        productServiceImpl.createProduct(new Product(
            9L, 
            "TCL20E 6125F1 (128GB)",
            "Respaldado por su alto rendimiento, el equipo con tres cámaras, tecnología NXTVISION y batería de 4000mAhcon administración inteligente te va a permitir obtener una experiencia de usuario única. Posee una memoria interna (ROM) de 128 GB. Vas a poder almacenar videos, fotos y música sin problemas.", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            220.00, 
            100.0,
            Status.NOT_POPULAR, 
            Type.PRODUCT, 
            "The Creative Life", 
            150.0, 
            "Octa-Core Mediatek", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(3L),
            emptyReviews, fillImagesToP9()
        ));

        productServiceImpl.createProduct(new Product(
            10L, 
            "XIAOMI 11 LITE NE (128GB)",
            "El campeón en peso pluma de la gama media se redefine a nivel de potencia", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            224.00, 
            100.0,
            Status.POPULAR, 
            Type.PRODUCT, 
            "Xiaomi", 
            158.0, 
            "Qualcomm Snapdragon 778G", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(3L),
            emptyReviews, fillImagesToP10()
        ));

        productServiceImpl.createProduct(new Product(
            11L, 
            "NOKIA G10 (64GB)",
            "La estética de los G10 y G20 mantiene esa curvatura en la trasera, coronada por un módulo circular de cámaras como hemos visto previamente en los móviles de la marca.", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            200.00, 
            100.0,
            Status.NOT_POPULAR, 
            Type.PRODUCT, 
            "Nokia", 
            194.0, 
            "MediaTek Helio G25 Octa-Core de 2GHz", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(3L),
            emptyReviews, fillImagesToP11()
        ));

        productServiceImpl.createProduct(new Product(
            12L, 
            "SAMSUNG A03 CORE (32GB)",
            "El nuevo teléfono de Samsung llega en color azul y en color negro, y en una única modalidad de RAM y almacenamiento interno.", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            140.00, 
            100.0,
            Status.NOT_POPULAR, 
            Type.PRODUCT, 
            "Samsumg", 
            160.0, 
            "Ocho núcleos a 1,6GHz", 
            0.0, new Date(),
            categoryServiceImpl.getCategory(3L),
            emptyReviews, fillImagesToP12()
        ));

        // 4 servicios para la categoria de servicio
        productServiceImpl.createProduct(new Product(
            13L, 
            "Super servicio 1",
            "3 Gigas + 3 Gigas para la noche por 15 dias",
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            3.00, 
            100.0,
            Status.NOT_POPULAR, 
            Type.SERVICE, 
            null, 
            null, 
            null, 
            0.0, new Date(),
            categoryServiceImpl.getCategory(4L),
            emptyReviews, 
            null
        ));

        productServiceImpl.createProduct(new Product(
            14L, 
            "Super servicio 2",
            "3 Gigas + 3 Gigas para la noche* + Llamadas Ilimitadas Por 3 Días",
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            5.00, 
            100.0,
            Status.POPULAR, 
            Type.SERVICE, 
            null, 
            null, 
            null, 
            0.0, new Date(),
            categoryServiceImpl.getCategory(4L),
            emptyReviews, 
            null
        ));

        productServiceImpl.createProduct(new Product(
            15L, 
            "Super servicio 3",
            "12 Gigas por 30 Días + 2 Gigas redes por 7 Días", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            12.00, 
            100.0,
            Status.POPULAR, 
            Type.SERVICE, 
            null, 
            null, 
            null, 
            0.0, new Date(),
            categoryServiceImpl.getCategory(4L),
            emptyReviews, 
            null
        ));

        productServiceImpl.createProduct(new Product(
            16L, 
            "Super servicio 4",
            "3.5 Gigas + 3.5 Gigas Para La Noche Por 20 Días", 
            "https://www.youtube.com/embed/TBTgQbjRsqg",
            7.00, 
            100.0,
            Status.POPULAR, 
            Type.SERVICE, 
            null, 
            null, 
            null, 
            0.0, new Date(),
            categoryServiceImpl.getCategory(4L),
            emptyReviews, 
            null
        ));

    }
}
