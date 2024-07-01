package bg.fmi.popcornpals.dto;

import bg.fmi.popcornpals.util.RatingConstraint;
import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long ID;

    @NotNull(message = "Review: Rating cannot be null")
    @Min(value = RatingConstraint.MIN, message = "Review: Rating must be at least " + RatingConstraint.MIN)
    @Max(value = RatingConstraint.MAX, message = "Review: Rating must be at most " + RatingConstraint.MAX)
    private Integer rating;

    @NotBlank(message = "Review: Review cannot be blank")
    @Size(max = StringSize.REVIEW_MAX, message = "Review: Review must be no more than " +  StringSize.REVIEW_MAX + "characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION , message = "Review: Invalid symbols in review")
    private String description;

    @NotNull(message = "Review: Date cannot be null")
    private LocalDate date;

    ReviewDTO(Integer rating, String description, LocalDate date) {
        this.rating = rating;
        this.description = description;
        this.date = date;
    }
}
