package fit.se.main.validator.khachhang;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import fit.se.main.service.khachhang.KhachHangService;

public class CreateEmaiValidator implements ConstraintValidator<ValidCreateEmail, String>{
	@Autowired
	private KhachHangService khachHangService;
	
	@Override
	public void initialize(ValidCreateEmail constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(khachHangService.findByEmail(email).isPresent()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Email already taken").addConstraintViolation();
			return false;
		}
		return true;
	}
	
}
