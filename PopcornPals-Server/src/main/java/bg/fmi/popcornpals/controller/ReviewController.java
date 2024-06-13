package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.mapper.ReviewMapper;
import bg.fmi.popcornpals.service.ReviewService;
import bg.fmi.popcornpals.dto.ReviewDTO;
import bg.fmi.popcornpals.model.Review;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/review/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMapper reviewMapper;

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO review) {
        review.setID(null);
        Review createdReview = reviewService.createReview(reviewMapper.toEntity(review));
        return new ResponseEntity<>(reviewMapper.toDTO(createdReview), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getStudios(
            @RequestParam(required = false) Long id){

        List<Review> reviewList = reviewService.getReview(id);

        if (reviewList == null || reviewList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ReviewDTO> reviewDTOList = reviewMapper.toDTOList(reviewList);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDTO review) {
        Review existingReview = reviewService.getReview(id).get(0);
        if (existingReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        review.setID(id);
        Review updatedReview = reviewService.updateReview(reviewMapper.toEntity(review));
        return new ResponseEntity<>(reviewMapper.toDTO(updatedReview), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        Review existingReview = reviewService.getReview(id).get(0);
        if (existingReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reviewService.deleteReviewById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllReview() {
        reviewService.deleteAllReview();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
