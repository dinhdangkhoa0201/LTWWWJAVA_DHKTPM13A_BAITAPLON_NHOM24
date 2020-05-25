package fit.se.main.dto;

import java.time.LocalDate;

import fit.se.main.validator.khachhang.ValidCreateEmail;
import fit.se.main.validator.khachhang.ValidCreatePhone;
import fit.se.main.validator.khachhang.ValidCreateUserName;
import fit.se.main.validator.khachhang.ValidEmail;
import fit.se.main.validator.khachhang.ValidPassword;
import fit.se.main.validator.khachhang.ValidPhone;
import fit.se.main.validator.khachhang.ValidUserName;

public class KhachHangUpdateDTO {
	@ValidUserName
	private String username;
	
	private LocalDate birthday;
	
	private String gender;
	
	@ValidPhone
	private String phone;
	
	@ValidEmail
	@ValidCreateEmail
	private String email;
	
	@ValidPassword
	private String password;
	
	private String repeatpassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
		return "KhachHangUpdateDTO [username=" + username + ", birthday=" + birthday + ", gender=" + gender + ", phone="
				+ phone + ", email=" + email + ", password=" + password + ", repeatpassword=" + repeatpassword + "]";
	}

	
}
