package fit.se.main.validator.account;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import fit.se.main.service.account.AccountService;

public class CreateUserNameValidator implements ConstraintValidator<ValidCreateUserName, String>{

	@Autowired
	private AccountService khachHangService;
	
	@Override
	public void initialize(ValidCreateUserName constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(khachHangService.findByEmail(username) != null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Username already taken").addConstraintViolation();
			return false;
		}
		return true;
	}

}
