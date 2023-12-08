package bg.softuni.water_app.model.dto.user;

import bg.softuni.water_app.model.entity.enums.UserRole;
import bg.softuni.water_app.model.validation.UniqueEmail;
import bg.softuni.water_app.model.validation.UniqueUsername;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserRegistrationDto {
    @NotBlank(message = "You must input a username!")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters!")
    @UniqueUsername
    private String username;
    @UniqueEmail
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Incorrect email")
    private String email;
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    @NotBlank(message = "You must input a password!")
    private String password;
    @NotBlank(message = "You must input a password confirmation!")
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "You must select a role!")
    private UserRole role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
