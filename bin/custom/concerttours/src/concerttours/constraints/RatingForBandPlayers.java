package concerttours.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RatingForBandPlayersValidator.class)
@Documented
public @interface RatingForBandPlayers {
    String message() default "{concerttours.constraints.OnlyBandPlayers.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
