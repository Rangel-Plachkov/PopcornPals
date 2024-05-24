package bg.fmi.popcornpals.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerDTO {
    private Long ID;
    private String name;
    private String description;
    private LocalDate birthdate;
}
