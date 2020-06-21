package fit.se.main.validator.account;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fit.se.main.util.RegexUtil;

public class UserNameValidator implements ConstraintValidator<ValidUserName, String>{

	@Override
	public void initialize(ValidUserName constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(username.isEmpty()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Username not be empty").addConstraintViolation();
			return false;
		} else if(!RegexUtil.validateUsername(username)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Username invalid").addConstraintViolation();
			return false;
		}
		return true;
	}
	
}
