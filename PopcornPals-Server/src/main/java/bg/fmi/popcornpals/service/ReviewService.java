package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.model.Review;
import bg.fmi.popcornpals.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getReview(Long ID) {
        if(ID == null) {
            return reviewRepository.findAll();
        } else {
            Review review = reviewRepository.findById(ID).orElse(null);
            return review == null ? null : List.of(review);
        }
    }

    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }
    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
    }
    public void deleteAllReview() {
        reviewRepository.deleteAll();
    }


}
