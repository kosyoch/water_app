package bg.softuni.water_app.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueGameTitleValidator.class)
public @interface UniqueGameTitle {
    String message() default "A game with this title already exists!";

    Class<?>[] groups()default {};

    Class<? extends Payload>[] payload() default {};
}
