package bg.fmi.popcornpals.model;

import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @NotNull(message = "Producer: Name cannot be null")
    @NotBlank(message = "Producer: Name cannot be blank")
    @Size(max = StringSize.PERSON_NAME_MAX, message = "Producer: Name must be no more than " + StringSize.PERSON_NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.PERSON_NAME, message = "Producer: Name can contain only letters, spaces, hyphens and apostrophes")
    private String name;

    @Size(max = StringSize.DESCRIPTION_MAX, message = "Producer: Description must be no more than " + StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "Producer: Invalid symbols in description")
    private String description;

    private LocalDate birthdate;

    @ManyToMany
    @JoinTable(name = "producer_media",
                joinColumns = @JoinColumn(name = "producer_id"),
                inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> producedMedia;

    public Producer(String name) {
        this.name = name;
    }
    public Producer(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }
    public Producer(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Producer(String name, String description, LocalDate birthdate) {
        this.name = name;
        this.description = description;
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof Producer producer)) {
            return false;
        }
        return name.equals(producer.name) &&
                description.equals(producer.description) &&
                birthdate.equals(producer.birthdate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, description, birthdate);
    }
}
