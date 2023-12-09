package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.dto.user.UserRegistrationDto;
import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.model.entity.enums.UserRole;
import bg.softuni.water_app.repository.UserRepository;
import bg.softuni.water_app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;




    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegistrationDto userRegistrationDto) {
        userRegistrationDto.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        User user = modelMapper.map(userRegistrationDto, User.class);
        user.setWallet(BigDecimal.valueOf(0));
        if(user.getUsername().equals("kosyoch")){
            user.setRole(UserRole.ADMIN);
        }
        userRepository.save(user);
    }



    @Override
    public void addFunds(BigDecimal moneyToAdd, String username) {
        User user = userRepository.findByUsername(username);
        BigDecimal currentFunds = user.getWallet();
        user.setWallet(currentFunds.add(moneyToAdd));
        userRepository.save(user);
    }

    @Override
    public BigDecimal getCurrentWallet(String username) {
        return userRepository.findByUsername(username).getWallet();
    }
}
