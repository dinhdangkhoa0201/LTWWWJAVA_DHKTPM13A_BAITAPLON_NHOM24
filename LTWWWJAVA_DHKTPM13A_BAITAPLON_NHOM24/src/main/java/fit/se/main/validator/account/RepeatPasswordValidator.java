package fit.se.main.validator.account;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fit.se.main.dto.AccountCreateDTO;
import fit.se.main.dto.AccountUpdateDTO;

public class RepeatPasswordValidator implements ConstraintValidator<ValidRepeatPassword, Object>{

	@Override
	public void initialize(ValidRepeatPassword constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value instanceof AccountCreateDTO) {
			AccountCreateDTO dto = (AccountCreateDTO) value;
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
		if(value instanceof AccountUpdateDTO) {
			AccountUpdateDTO dto = (AccountUpdateDTO) value;
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
