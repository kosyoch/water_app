package bg.softuni.water_app.model.validation;

import bg.softuni.water_app.repo.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserRepository userRepository;

    public UniqueUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return !userRepository.existsByUsername(value);
    }
}
