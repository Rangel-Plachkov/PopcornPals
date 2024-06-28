package bg.fmi.popcornpals.model;

import bg.fmi.popcornpals.util.StringSize;
import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.RatingConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    public Review(Integer rating, LocalDate date) {
        this.rating = rating;
        this.date = date;
    }
    public Review(Integer rating, String description, LocalDate date) {
        this.rating = rating;
        this.description = description;
        this.date = date;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return this.rating.equals(review.getRating())
                && this.description.equals(review.getDescription()) && this.date.equals(review.getDate());
    }
    @Override
    public int hashCode() {
        return Objects.hash(rating, description, date);
    }
}
