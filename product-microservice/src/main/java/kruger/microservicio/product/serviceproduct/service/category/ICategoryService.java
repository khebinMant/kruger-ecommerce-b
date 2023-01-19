package kruger.microservicio.product.serviceproduct.service.category;

import java.util.List;

import kruger.microservicio.product.serviceproduct.entity.Category;

/**
 * This microservice was created by Kevin Mantilla
 */
public interface ICategoryService {

    public List<Category> listAllCategories();
    public Category getCategory(Long id);

    public Category createCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(Long id);

}
