package bg.fmi.popcornpals.model;

import bg.fmi.popcornpals.enums.StringSize;
import bg.fmi.popcornpals.enums.RegexPattern;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    @NotNull(message = "Review: Rating cannot be null")
    @Min(value = 1, message = "Review: Rating must be between 1 and 10")
    @Max(value = 10, message = "Review: Rating must be between 1 and 10")
    private Integer rating;

    @NotBlank(message = "Review: Review cannot be blank")
    @Size(max = StringSize.REVIEW_MAX, message = "Review: Review must be no more than " +  StringSize.REVIEW_MAX + "characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION , message = "Review: Invalid symbols in review")
    private String description;

    @NotNull(message = "Review: Date cannot be null")
    private LocalDate date;

    public Review(Integer rating, LocalDate date) {
        this.rating = rating;
        this.date = date;
    }
    public Review(Integer rating, String description, LocalDate date) {
        this.rating = rating;
        this.description = description;
        this.date = date;
    }
    public boolean equals(Review review) {
        return this.rating.equals(review.getRating()) && this.description.equals(review.getDescription()) && this.date.equals(review.getDate());
    }
}
