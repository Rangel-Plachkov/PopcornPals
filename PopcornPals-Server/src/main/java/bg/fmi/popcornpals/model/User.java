package bg.fmi.popcornpals.model;

import bg.fmi.popcornpals.util.RegexPattern;
import bg.fmi.popcornpals.util.StringSize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @NotNull(message = "User: Username cannot be null")
    @NotBlank(message = "User: Username cannot be blank")
    @Size(min = StringSize.USERNAME_MIN, max = StringSize.USERNAME_MAX, message = "User: Username must be between " + StringSize.USERNAME_MIN + " and " + StringSize.USERNAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.USERNAME, message = "User: Username must begin with a letter")
    private String username;

    @NotNull(message = "User: Password cannot be null")
    @NotBlank(message = "User: Password cannot be blank")
    @Size(min = StringSize.PASSWORD_MIN, max = StringSize.PASSWORD_MAX, message = "User: Password must be between " + StringSize.PASSWORD_MIN + " and " + StringSize.PASSWORD_MAX  + " characters")
    @Pattern(regexp = RegexPattern.PASSWORD, message = "User: Password must contain at least one lowercase and uppercase letter, one digit and one special symbol")
    private String password;

    @NotNull(message = "User: Name cannot be null")
    @NotBlank(message = "User: Name cannot be blank")
    @Size(max = StringSize.PERSON_NAME_MAX, message = "User: Name must be no more than " + StringSize.PERSON_NAME_MAX + " characters")
    @Pattern(regexp = RegexPattern.PERSON_NAME, message = "User: Name can contain only letters, spaces, hyphens and apostrophes")
    private String name;

    @NotBlank(message = "User: Email cannot be blank")
    @Email(message = "User: Email must be of valid email Id")
    private String email;

    @Size(max = StringSize.DESCRIPTION_MAX, message = "User: Description must be no more than " + StringSize.DESCRIPTION_MAX + " characters")
    @Pattern(regexp = RegexPattern.DESCRIPTION, message = "User: Invalid symbols in description")
    private String description;

    private LocalDate birthday;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Playlist> playlists;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }
    public User(String username, String password, String name, String email, String description) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.description = description;
    }
    public User(String username, String password, String name, String email, LocalDate birthday) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }
    public User(String username, String password, String name, String email, String description, LocalDate birthday) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.description = description;
        this.birthday = birthday;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return username.equals(user.username) && password.equals(user.password) &&
                name.equals(user.name) && email.equals(user.email) &&
                description.equals(user.description) && birthday.equals(user.birthday);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, password, name, email, description, birthday);
    }
}
