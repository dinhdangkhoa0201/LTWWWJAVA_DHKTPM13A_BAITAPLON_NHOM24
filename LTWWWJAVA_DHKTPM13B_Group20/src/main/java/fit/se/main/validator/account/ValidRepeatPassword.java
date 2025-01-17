package fit.se.main.validator.account;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RepeatPasswordValidator.class)
@Documented
public @interface ValidRepeatPassword {
	String message() default "Passwords don't match.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
