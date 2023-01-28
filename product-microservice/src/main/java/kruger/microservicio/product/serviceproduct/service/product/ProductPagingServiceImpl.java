package kruger.microservicio.product.serviceproduct.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.entity.Product;
import kruger.microservicio.product.serviceproduct.repository.ProductPagingRepository;

@Service
public class ProductPagingServiceImpl implements IProductPagingService{

    @Autowired
    ProductPagingRepository productPagingRepository;

    @Override
    public List<Product> getAllProductsByCategory(Integer pageNo, Integer pageSize, String sortBy, Category category) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Product> pagedResult = productPagingRepository.findByCategory(category, paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }

    @Override
    public List<Product> getAllProductsByName(Integer pageNo, Integer pageSize, String sortBy) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> getAllProductPaginated(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Product> pagedResult = productPagingRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }
    
}
