package bg.fmi.popcornpals.model;

import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
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
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @NotNull(message = "Actor: Name cannot be null")
    @NotBlank(message = "Actor: Name cannot be blank")
    @Size(max = StringSize.NAME_MAX, message = "Actor: Name must be no more than " + StringSize.NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.NAME, message = "Actor: Name can contain only letters, digits and spaces")
    private String name;

    @Size(max = StringSize.DESCRIPTION_MAX, message = "Actor: Description must be no more than " + StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "Actor: Invalid symbols in description")
    private String description;
    private LocalDate birthdate;

    public Actor(String name) {
        this.name = name;
    }
    public Actor(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }
    public Actor(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Actor(String name, String description, LocalDate birthdate) {
        this.name = name;
        this.description = description;
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof Actor actor)) {
            return false;
        }
        return name.equals(actor.name) &&
                description.equals(actor.description) &&
                birthdate.equals(actor.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, birthdate);
    }
}
