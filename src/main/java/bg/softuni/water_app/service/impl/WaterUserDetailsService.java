package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class WaterUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public WaterUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null){
            return user;
        }
        throw new UsernameNotFoundException("User " + username + "not found!");


    }
}
