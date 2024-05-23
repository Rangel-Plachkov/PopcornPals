package bg.fmi.popcornpals.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO {
    private Long Id;
    private String name;
    private String description;
    private LocalDate birthdate;
}
