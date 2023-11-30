package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.repo.UserRepository;
import bg.softuni.water_app.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    private Long loginUserId;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;

    }



    @Override
    public User getLoggedUser() {
        return userRepository.findById(loginUserId).get();
    }
}
