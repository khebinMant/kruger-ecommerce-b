package kruger.microservicio.product.serviceproduct.service.review;

import java.util.List;

import kruger.microservicio.product.serviceproduct.entity.Review;

public interface IReviewService {

    public List<Review> listAllReviews();
    public Review getReview(Long id);

    public Review createReview(Review review);
    public Review updateReview(Review review);
    public void deleteReview(Long id);

}
