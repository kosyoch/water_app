package bg.softuni.water_app.model.validation;

import bg.softuni.water_app.repository.GameRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueGameTitleValidator implements ConstraintValidator<UniqueGameTitle, String> {
    private final GameRepository gameRepository;

    public UniqueGameTitleValidator(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return gameRepository.findByTitle(value).isEmpty();
    }

}
