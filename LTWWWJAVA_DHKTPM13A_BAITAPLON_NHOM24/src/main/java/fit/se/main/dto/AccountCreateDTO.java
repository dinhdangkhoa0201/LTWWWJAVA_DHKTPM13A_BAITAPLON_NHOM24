package fit.se.main.dto;

public class AccountCreateDTO {
	private int id;
	
	private String accountName;
	
	private String birthday;
	
	private String gender;
	
	private String email;
	
	private String phone;

	private String address;
	
	private String password;
	
	private String repeatpassword;
	
	private String note;

	public AccountCreateDTO(String accountName, String phone, String email, String password, String repeatpassword) {
		this.accountName = accountName;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.repeatpassword = repeatpassword;
	}
	
	public AccountCreateDTO(String accountName, String birthday, String gender, String email, String phone,
			String address, String password, String note) {
		this.accountName = accountName;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.note = note;
	}

	public AccountCreateDTO() {
	}

	public AccountCreateDTO(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getBirthday() {
		return birthday;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public String getNote() {
		return note;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "KhachHangCreateDTO [id=" + id + ", username=" + accountName + ", phone=" + phone + ", email=" + email
				+ ", password=" + password + ", repeatpassword=" + repeatpassword + "]";
	}
	
}
