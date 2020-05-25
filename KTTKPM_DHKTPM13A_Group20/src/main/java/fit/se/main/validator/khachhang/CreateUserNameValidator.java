package fit.se.main.validator.khachhang;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import fit.se.main.service.khachhang.KhachHangService;

public class CreateUserNameValidator implements ConstraintValidator<ValidCreateUserName, String>{

	@Autowired
	private KhachHangService khachHangService;
	
	@Override
	public void initialize(ValidCreateUserName constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(khachHangService.findByHoTen(username).isPresent()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Username already taken").addConstraintViolation();
			return false;
		}
		return true;
	}

}
