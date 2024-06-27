package bg.fmi.popcornpals.service;

import bg.fmi.popcornpals.dto.ReviewRequestDTO;
import bg.fmi.popcornpals.dto.ReviewDTO;
import bg.fmi.popcornpals.exception.notfound.ReviewNotFoundException;
import bg.fmi.popcornpals.exception.notfound.UserNotFoundException;
import bg.fmi.popcornpals.exception.notfound.MediaNotFoundException;
import bg.fmi.popcornpals.mapper.ReviewMapper;
import bg.fmi.popcornpals.model.Media;
import bg.fmi.popcornpals.model.Review;
import bg.fmi.popcornpals.model.User;
import bg.fmi.popcornpals.repository.MediaRepository;
import bg.fmi.popcornpals.repository.ReviewRepository;
import bg.fmi.popcornpals.repository.UserRepository;
import bg.fmi.popcornpals.util.ReviewSortTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private ReviewMapper reviewMapper;

    public ReviewDTO createReview(ReviewRequestDTO review) {
        User user = userRepository.findById(review.getUserID())
                .orElseThrow(UserNotFoundException::new);
        Media media = mediaRepository.findById(review.getMediaID())
                .orElseThrow(MediaNotFoundException::new);

        Review newReview = reviewMapper.toEntity(review);
        newReview.setCreator(user);
        newReview.setMedia(media);
        Review savedReview = reviewRepository.save(newReview);
        return reviewMapper.toDTO(savedReview);
    }
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new);
        return reviewMapper.toDTO(review);
    }

    public List<ReviewDTO> getReview(Long mediaID, Long userID, ReviewSortTypes sortType, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Review> reviews = null;

        if(mediaID != null && userID != null) {
            Media media = mediaRepository.findById(mediaID)
                    .orElseThrow(MediaNotFoundException::new);
            User user = userRepository.findById(userID)
                    .orElseThrow(UserNotFoundException::new);
            reviews =  reviewRepository.findByMediaAndCreator(media, user, pageable);
        }else if(mediaID != null) {
            Media media = mediaRepository.findById(mediaID)
                    .orElseThrow(MediaNotFoundException::new);
            reviews = reviewRepository.findByMedia(media, pageable);
        }else if(userID != null) {
            User user = userRepository.findById(userID)
                    .orElseThrow(UserNotFoundException::new);
            reviews = reviewRepository.findByCreator(user, pageable);
        }else{
            reviews = reviewRepository.findAll(pageable);
        }
        return reviewMapper.toDTOList(reviews.getContent());
    }

    public ReviewDTO updateReview(Long reviewID , ReviewRequestDTO review) {
        Review existingReview = reviewRepository.findById(reviewID)
                .orElseThrow(ReviewNotFoundException::new);
        existingReview = reviewMapper.toEntity(review);
        return reviewMapper.toDTO(reviewRepository.save(existingReview));
    }
    public void deleteReviewById(Long id) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new);
        reviewRepository.delete(existingReview);
    }

}
