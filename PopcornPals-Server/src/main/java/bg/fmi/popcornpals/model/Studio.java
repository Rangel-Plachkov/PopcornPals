package bg.fmi.popcornpals.model;

import bg.fmi.popcornpals.util.StringSize;
import bg.fmi.popcornpals.util.RegexPattern;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Studio {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @NotNull(message = "Studio: Name cannot be null")
    @NotBlank(message = "Studio: Name cannot be blank")
    @Size(max = StringSize.STUDIO_NAME_MAX, message = "Studio: Name must be no more than " +  StringSize.STUDIO_NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.STUDIO_NAME, message = "Studio: Name can contain only letters, digits and spaces")
    private String name;

    @Size(max = StringSize.DESCRIPTION_MAX, message = "Studio: Description must be no more than " +  StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "Studio: Invalid symbols in description")
    private String description;

    private LocalDate foundingDate;

    @OneToMany(mappedBy = "studio")
    private List<Media> media;

    public Studio(String name) {
        this.name = name;
    }
    public Studio(String name, LocalDate foundingDate) {
        this.name = name;
        this.foundingDate = foundingDate;
    }
    public Studio(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Studio(String name, String description, LocalDate foundingDate) {
        this.name = name;
        this.description = description;
        this.foundingDate = foundingDate;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studio studio)) return false;
        return name.equals(studio.name)
                && description.equals(studio.description) && foundingDate.equals(studio.foundingDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, description, foundingDate);
    }

}
