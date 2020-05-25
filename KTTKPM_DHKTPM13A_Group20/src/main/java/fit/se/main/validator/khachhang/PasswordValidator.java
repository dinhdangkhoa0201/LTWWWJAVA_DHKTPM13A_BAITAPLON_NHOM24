package fit.se.main.validator.khachhang;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fit.se.main.util.RegexUtil;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String>{

	@Override
	public void initialize(ValidPassword constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		Map<String, String> maps = new HashMap<String, String>();
		if(!RegexUtil.validatePassword(password, maps)) {
			String errorMessage = maps.get("errorMessage");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
			return false;
		}
		return true;
	}

}
