package fit.se.main.dto;

import java.time.LocalDate;

import fit.se.main.validator.khachhang.ValidCreateEmail;
import fit.se.main.validator.khachhang.ValidCreatePhone;
import fit.se.main.validator.khachhang.ValidCreateUserName;
import fit.se.main.validator.khachhang.ValidEmail;
import fit.se.main.validator.khachhang.ValidPassword;
import fit.se.main.validator.khachhang.ValidPhone;
import fit.se.main.validator.khachhang.ValidUserName;

public class KhachHangCreateDTO {
	private Long id;
	
	@ValidUserName
	@ValidCreateUserName
	private String username;
	
	@ValidPhone
	@ValidCreatePhone
	private String phone;
	
	@ValidEmail
	@ValidCreateEmail
	private String email;
	
	@ValidPassword
	private String password;
	
	private String repeatpassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatpassword() {
		return repeatpassword;
	}

	public void setRepeatpassword(String repeatpassword) {
		this.repeatpassword = repeatpassword;
	}

	@Override
	public String toString() {
		return "KhachHangCreateDTO [id=" + id + ", username=" + username + ", phone=" + phone + ", email=" + email
				+ ", password=" + password + ", repeatpassword=" + repeatpassword + "]";
	}

	
}
