package bg.softuni.water_app.model.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    CUSTOMER, DEVELOPER, ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
