package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.dto.user.UserLoginBindingModel;
import bg.softuni.water_app.model.dto.user.UserRegisterBindingModel;
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
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            return false;
        }
        boolean existsByUsernameOrEmail = userRepository.existsByUsernameOrEmail(
                userRegisterBindingModel.getUsername(),
                userRegisterBindingModel.getEmail());
        if(existsByUsernameOrEmail){
            return false;
        }
        User user = new User();
        user.setUsername(userRegisterBindingModel.getUsername());
        user.setEmail(userRegisterBindingModel.getEmail());
        user.setPassword(userRegisterBindingModel.getPassword());

        userRepository.save(user);
        return true;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        String username = userLoginBindingModel.getUsername();
        User user = userRepository.findByUsername(username);
        if(user != null &&
                passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword())){
            loggedUser.login(username);
            loginUserId = user.getId();
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }

    @Override
    public User getLoggedUser() {
        return userRepository.findById(loginUserId).get();
    }
}
