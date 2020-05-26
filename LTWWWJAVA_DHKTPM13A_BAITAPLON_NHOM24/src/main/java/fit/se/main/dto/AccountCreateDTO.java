package fit.se.main.dto;

public class AccountCreateDTO {
	private Long id;
	
	private String accountName;
	
	private String phone;
	
	private String email;
	
	private String password;
	
	private String repeatpassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
		return "KhachHangCreateDTO [id=" + id + ", username=" + accountName + ", phone=" + phone + ", email=" + email
				+ ", password=" + password + ", repeatpassword=" + repeatpassword + "]";
	}
	
}
