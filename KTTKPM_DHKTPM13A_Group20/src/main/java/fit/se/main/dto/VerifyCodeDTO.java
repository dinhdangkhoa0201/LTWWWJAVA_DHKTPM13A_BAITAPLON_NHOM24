package fit.se.main.dto;

import fit.se.main.validator.verifyaccount.ValidVerifyCode;

public class VerifyCodeDTO {
	@ValidVerifyCode
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
