package fit.se.main.validator.verifyaccount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import fit.se.main.dao.verifyaccount.VerifyAccountDAO;
import fit.se.main.model.VerifyAccount;

public class VerifyCodeValidator implements ConstraintValidator<ValidVerifyCode, String>{

	@Autowired
	private VerifyAccountDAO verifyAccountDAO;
	
	@Override
	public boolean isValid(String token, ConstraintValidatorContext context) {
		if(token.isEmpty()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Code not be empty").addConstraintViolation();
			return false;
		} else if(!verifyAccountDAO.findByToken(token).isPresent()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Code verification not found").addConstraintViolation();
			return false;
		} else {
			VerifyAccount verifyAccount = verifyAccountDAO.findByToken(token).get();
			if(verifyAccount.isExpired()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Verification code has expired").addConstraintViolation();
				return false;
			}
		}
		return true;
	}

}
