package bg.fmi.popcornpals.dto;

import bg.fmi.popcornpals.util.RatingConstraint;
import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDTO {

    @NotNull(message = "Review: User ID cannot be null")
    private Long userID;
    @NotNull(message = "Review: Movie ID cannot be null")
    private Long movieID;

    @NotNull(message = "Review: Rating cannot be null")
    @Min(value = RatingConstraint.MIN, message = "Review: Rating must be at least " + RatingConstraint.MIN)
    @Max(value = RatingConstraint.MAX, message = "Review: Rating must be at most " + RatingConstraint.MAX)
    private Integer rating;

    @NotBlank(message = "Review: Review cannot be blank")
    @Size(max = StringSize.REVIEW_MAX, message = "Review: Review must be no more than " +  StringSize.REVIEW_MAX + "characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION , message = "Review: Invalid symbols in review")
    private String description;

    public ReviewRequestDTO(Long userID, Long movieID, Integer rating) {
        this.userID = userID;
        this.movieID = movieID;
        this.rating = rating;
    }
}
