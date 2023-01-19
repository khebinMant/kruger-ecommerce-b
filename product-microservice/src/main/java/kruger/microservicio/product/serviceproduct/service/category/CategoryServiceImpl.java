package kruger.microservicio.product.serviceproduct.service.category;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.repository.CategoryRepository;

/**
 * This microservice was created by Kevin Mantilla
 */
@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        category.setCreated(new Date());
        category.setDescription(category.getDescription());
        category.setName(category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category categoryDB = getCategory(category.getId());
        if(categoryDB == null){
            return null;
        }
        categoryDB.setDescription(category.getDescription());
        categoryDB.setName(category.getName());
        
        return categoryRepository.save(categoryDB);
    }

    @Override
    public void deleteCategory(Long id) {
        Category categoryDB = getCategory(id);
        categoryRepository.delete(categoryDB);
    }
    
}
