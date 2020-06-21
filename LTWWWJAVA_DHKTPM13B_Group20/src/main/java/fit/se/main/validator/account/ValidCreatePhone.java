package fit.se.main.validator.account;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CreatePhoneValidator.class)
public @interface ValidCreatePhone {
	String message() default "Invalid phone number.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
