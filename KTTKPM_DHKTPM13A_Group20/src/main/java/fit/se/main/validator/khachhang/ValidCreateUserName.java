package fit.se.main.validator.khachhang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CreateUserNameValidator.class)
public @interface ValidCreateUserName {
	String message() default "Invalid username.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
