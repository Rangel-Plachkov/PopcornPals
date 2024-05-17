package bg.fmi.popcornpals.model;

import bg.fmi.popcornpals.util.StringSize;
import bg.fmi.popcornpals.util.RegexPattern;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Studio {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    @NotNull(message = "Studio: Name cannot be null")
    @NotBlank(message = "Studio: Name cannot be blank")
    @Size(max = StringSize.NAME_MAX, message = "Studio: Name must be no more than " +  StringSize.NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.NAME, message = "Studio: Name can contain only letters, digits and spaces")
    private String name;

    @Size(max = StringSize.DESCRIPTION_MAX, message = "Studio: Description must be no more than " +  StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "Studio: Invalid symbols in description")
    private String description;

    private LocalDate founded;

    public Studio(String name) {
        this.name = name;
    }
    public Studio(String name, LocalDate founded) {
        this.name = name;
        this.founded = founded;
    }
    public Studio(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Studio(String name, String description, LocalDate founded) {
        this.name = name;
        this.description = description;
        this.founded = founded;
    }
    public boolean equals(Studio studio) {
        return this.name.equals(studio.getName()) && this.description.equals(studio.getDescription()) && this.founded.equals(studio.getFounded());
    }



}
