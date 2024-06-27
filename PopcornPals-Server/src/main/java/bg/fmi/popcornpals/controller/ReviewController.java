package bg.fmi.popcornpals.controller;

import bg.fmi.popcornpals.dto.ReviewRequestDTO;
import bg.fmi.popcornpals.service.ReviewService;
import bg.fmi.popcornpals.dto.ReviewDTO;
import bg.fmi.popcornpals.util.PaginationProperties;
import bg.fmi.popcornpals.util.ReviewSortTypes;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/api/review/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewRequestDTO review) {
        return new ResponseEntity<>(reviewService.createReview(review), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getReviewById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getReview(
            @RequestParam(value = "mediaID" , required = false) Long mediaID,
            @RequestParam(value = "userID" ,required = false) Long userID,
            @RequestBody(required = false)ReviewSortTypes sortType,
            @RequestParam(value = "pageNo", defaultValue = PaginationProperties.DEFAULT_PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationProperties.DEFAULT_PAGE_SIZE, required = false) Integer pageSize) {
        return new ResponseEntity<>(reviewService.getReview(mediaID, userID, sortType, pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewRequestDTO review) {
        return new ResponseEntity<>(reviewService.updateReview(id, review), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReviewById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
