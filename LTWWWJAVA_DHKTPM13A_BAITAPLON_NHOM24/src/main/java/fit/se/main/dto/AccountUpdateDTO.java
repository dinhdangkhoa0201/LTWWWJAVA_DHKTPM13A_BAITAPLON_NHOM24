package fit.se.main.dto;

import java.time.LocalDate;

import fit.se.main.validator.account.ValidCreateEmail;
import fit.se.main.validator.account.ValidEmail;
import fit.se.main.validator.account.ValidPassword;
import fit.se.main.validator.account.ValidPhone;
import fit.se.main.validator.account.ValidUserName;

public class AccountUpdateDTO {
	
	private String accountName;
	
	private LocalDate birthday;
	
	private String gender;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String note;
	
	private String password;
	
	private String repeatpassword;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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

	public String getAddress() {
		return address;
	}

	public String getNote() {
		return note;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "KhachHangUpdateDTO [username=" + accountName + ", birthday=" + birthday + ", gender=" + gender + ", phone="
				+ phone + ", email=" + email + ", password=" + password + ", repeatpassword=" + repeatpassword + "]";
	}

	
}
