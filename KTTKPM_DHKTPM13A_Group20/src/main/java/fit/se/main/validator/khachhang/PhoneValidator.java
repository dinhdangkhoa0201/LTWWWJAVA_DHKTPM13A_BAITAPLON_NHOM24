package fit.se.main.validator.khachhang;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fit.se.main.util.RegexUtil;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String>{
	@Override
	public void initialize(ValidPhone constraintAnnotation) {
	}

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		if(phone.isEmpty()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Phone number is not empty").addConstraintViolation();
		} else if(!RegexUtil.validatePhonenumber(phone)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Phone number invalid").addConstraintViolation();
			return false;
		}
		return true;
	}
	
	
}
