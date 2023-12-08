package bg.softuni.water_app.model.entity;

import bg.softuni.water_app.model.entity.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;


@Entity
@Table
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usernames", nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)

    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Transient
    private String confirmPassword;

    private BigDecimal wallet;

    public User() {
    }

    // region getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    /**
     * Our application will not use this spring security functionality at the moment.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Our application will not use this spring security functionality at the moment.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Our application will not use this spring security functionality at the moment.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Our application will not use this spring security functionality at the moment.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    // endregion
}
