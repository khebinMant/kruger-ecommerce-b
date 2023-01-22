package kruger.microservicio.product.serviceproduct.service.review;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kruger.microservicio.product.serviceproduct.entity.Review;
import kruger.microservicio.product.serviceproduct.repository.ReviewRepository;


@Service
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> listAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReview(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review createReview(Review review) {
        review.setCreated(new Date());
        review.setRaiting(review.getRaiting());
        review.setUserId(review.getUserId());
        review.setText(review.getText());
        review.setProductId(review.getProductId());

        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) {
        Review reviewDB = getReview(review.getId());
        if(reviewDB == null){
            return null;
        }
        reviewDB.setRaiting(review.getRaiting());
        reviewDB.setText(review.getText());
        
        return reviewRepository.save(reviewDB);
    }

    @Override
    public void deleteReview(Long id) {
        Review reviewDB = getReview(id);
        reviewRepository.delete(reviewDB);
    }
    
}
