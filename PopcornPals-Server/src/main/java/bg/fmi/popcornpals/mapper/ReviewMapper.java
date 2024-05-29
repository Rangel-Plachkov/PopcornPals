package bg.fmi.popcornpals.mapper;

import bg.fmi.popcornpals.model.Review;
import bg.fmi.popcornpals.dto.ReviewDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "description", target = "description")
    ReviewDTO toDTO(Review review);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "description", target = "description")
    Review toEntity(ReviewDTO reviewDTO);

    List<ReviewDTO> toDTOList(List<Review> reviewList);
    List<Review> toEntityList(List<ReviewDTO> reviewDTOList);

    @AfterMapping
    default void setDate(@MappingTarget Review destination) {
        destination.setDate(LocalDate.from(LocalDateTime.now()));
    }
}
