package kruger.microservicio.product.serviceproduct.service.category;

import java.util.List;

import kruger.microservicio.product.serviceproduct.entity.Category;


public interface ICategoryService {

    public List<Category> listAllCategories();
    public Category getCategory(Long id);

    public Category createCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(Long id);

}
