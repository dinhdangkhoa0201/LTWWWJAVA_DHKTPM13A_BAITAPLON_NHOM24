package fit.se.main.validator.khachhang;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fit.se.main.dto.KhachHangCreateDTO;
import fit.se.main.dto.KhachHangUpdateDTO;

public class RepeatPasswordValidator implements ConstraintValidator<ValidRepeatPassword, Object>{

	@Override
	public void initialize(ValidRepeatPassword constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value instanceof KhachHangCreateDTO) {
			KhachHangCreateDTO dto = (KhachHangCreateDTO) value;
			if(dto.getPassword().isEmpty()) {
				context.buildConstraintViolationWithTemplate("Password not be empty")
				.addPropertyNode("repeatpassword")
				.addConstraintViolation();
				return false;
			}
			if(!dto.getPassword().equals(dto.getRepeatpassword())) {
				context.buildConstraintViolationWithTemplate("Password don't match")
				.addPropertyNode("repeatpassword")
				.addConstraintViolation();
				return false;
			}
		}
		if(value instanceof KhachHangUpdateDTO) {
			KhachHangUpdateDTO dto = (KhachHangUpdateDTO) value;
			if(dto.getPassword().isEmpty()) {
				context.buildConstraintViolationWithTemplate("Password not be empty")
				.addPropertyNode("repeatpassword")
				.addConstraintViolation();
				return false;
			}
			if(!dto.getPassword().equals(dto.getRepeatpassword())) {
				context.buildConstraintViolationWithTemplate("Password don't match")
				.addPropertyNode("repeatpassword")
				.addConstraintViolation();
				return false;
			}
		}
		return true;
	}

}
