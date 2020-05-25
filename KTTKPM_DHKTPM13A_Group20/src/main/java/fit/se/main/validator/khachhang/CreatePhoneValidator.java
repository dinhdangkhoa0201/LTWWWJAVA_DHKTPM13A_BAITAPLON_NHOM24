package fit.se.main.validator.khachhang;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import fit.se.main.service.khachhang.KhachHangService;

public class CreatePhoneValidator implements ConstraintValidator<ValidCreatePhone, String>{
	@Autowired
	private KhachHangService khachHangService;

	@Override
	public void initialize(ValidCreatePhone constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		if(khachHangService.findByPhone(phone).isPresent()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Phone number already taken").addConstraintViolation();
			return false;
		}
		return true;
	}
	
	
}
