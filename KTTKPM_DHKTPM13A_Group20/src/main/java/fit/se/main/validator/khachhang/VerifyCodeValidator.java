package fit.se.main.validator.khachhang;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import fit.se.main.validator.verifyaccount.ValidVerifyCode;

public class VerifyCodeValidator implements ConstraintValidator<ValidVerifyCode, String>{
	@Autowired
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
