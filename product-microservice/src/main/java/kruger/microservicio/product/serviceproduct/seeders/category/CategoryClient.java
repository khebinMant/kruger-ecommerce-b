package kruger.microservicio.product.serviceproduct.seeders.category;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.service.category.CategoryServiceImpl;


@Component
public class CategoryClient {
    

    @Autowired
    CategoryServiceImpl categoryServiceImpl;


    //Me sirve para llenar de categorias la base de datos cuando recién empieza
    public void fillCategoriesDataBase(){

        categoryServiceImpl.createCategory( new Category(1L,"Gama Baja","Dipositivo catalogado como gama baja",new Date()));
        categoryServiceImpl.createCategory(new Category(2L,"Gama Media","Dipositivo catalogado como gama media",new Date()));
        categoryServiceImpl.createCategory(new Category(4L,"Gama Alta","Dipositivo catalogado como gama alta",new Date()));
        categoryServiceImpl.createCategory(new Category(5L,"Servicio","Este es un servicio ",new Date()));
    }

}
