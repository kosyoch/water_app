package bg.softuni.water_app.config;

import bg.softuni.water_app.model.entity.enums.UserRole;
import bg.softuni.water_app.repository.UserRepository;
import bg.softuni.water_app.service.impl.WaterUserDetailsService;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
      return httpSecurity
              .authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/", "/login", "/register", "/login-error").permitAll()
                .requestMatchers("/games/add").hasAnyRole(UserRole.DEVELOPER.name(), UserRole.ADMIN.name())
                .requestMatchers("/reviews/add", "/game-keys/buy","/wallet/add" ).hasAnyRole(UserRole.CUSTOMER.name(), UserRole.ADMIN.name())
                .requestMatchers( "/reviews/{id}/remove","/games/{id}/remove").hasRole(UserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
              .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .failureForwardUrl("/login-error")
              .and()
              .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
              .and()
              .build();
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new WaterUserDetailsService(userRepository);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
