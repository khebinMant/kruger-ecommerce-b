package kruger.microservicio.product.serviceproduct.client.category;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.service.category.CategoryServiceImpl;

/**
 * This microservice was created by Kevin Mantilla
 */
@Component
public class CategoryClient {
    

    @Autowired
    CategoryServiceImpl categoryServiceImpl;


    //Me sirve para llenar de categorias la base de datos cuando recién empieza
    public void fillCategoriesDataBase(){

        Category category1 = categoryServiceImpl.createCategory( new Category(1L,"calzado","Categoría donde pertecen los productos de tipo calzado",new Date()));
        Category category2 = categoryServiceImpl.createCategory(new Category(2L,"electrónico","Categoría donde pertecen los productos de tipo electrónicos",new Date()));
        Category category4 = categoryServiceImpl.createCategory(new Category(4L,"hogar","Categoría donde pertecen instrumentos o contenido musical",new Date()));
    }

}
