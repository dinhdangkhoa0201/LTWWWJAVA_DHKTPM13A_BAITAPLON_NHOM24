package fit.se.main.validator.account;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fit.se.main.util.RegexUtil;

public class EmailValidator implements ConstraintValidator<ValidEmail, String>{
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(email.isEmpty()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Email not be empty").addConstraintViolation();
			return false;
		}
		if(!RegexUtil.validateEmail(email)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Email invalid").addConstraintViolation();
			return false;
		}
		return true;
	}
	
}
