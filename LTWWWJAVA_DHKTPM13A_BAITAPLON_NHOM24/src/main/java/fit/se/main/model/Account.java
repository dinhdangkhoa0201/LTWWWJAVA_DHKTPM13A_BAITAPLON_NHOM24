package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id", nullable = false)
	private int accountId;

	@Column(name = "account_name", nullable = false, columnDefinition = "nvarchar(50)")
	private String accountName;

	@Column(name = "birthday")
	private String birthday;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "address", columnDefinition = "nvarchar(100)")
	private String address;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "note", columnDefinition = "nvarchar(255)")
	private String note;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "account_role", joinColumns = {
			@JoinColumn(name = "account_id")}, inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roles;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
	private List<VerifyAccount> verificationTokens;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
	private List<SaleOrderHeader> orders;

	private boolean isEnabled;

	private LocalDateTime modifiedDate;

	public Account(int accountId, String accountName, String birthday, String gender, String email, String phone,
			String address, String password, String note) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.note = note;
		this.modifiedDate = LocalDateTime.now();
	}

	public Account(String accountName, String email, String phone, String address, String password) {
		this.accountName = accountName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.modifiedDate = LocalDateTime.now();
	}

	public Account(String userName, String email, String phone, String address, String password, String note) {
		this.accountName = userName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.note = note;
		this.modifiedDate = LocalDateTime.now();
	}

	public Account(int accountId) {
		this.accountId = accountId;
	}

	public Account() {
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<VerifyAccount> getVerificationTokens() {
		return verificationTokens;
	}

	public void setVerificationTokens(List<VerifyAccount> verificationTokens) {
		this.verificationTokens = verificationTokens;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public List<SaleOrderHeader> getOrders() {
		return orders;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setOrders(List<SaleOrderHeader> orders) {
		this.orders = orders;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setAddress(String addres) {
		this.address = addres;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void addRole(Role role) {
		if(roles == null) {
			roles = new ArrayList<Role>();
		}
		roles.add(role);
	}
	
	public void clearRole() {
		roles.clear();
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", birthday=" + birthday
				+ ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", password=" + password + ", note=" + note + ", isEnabled=" + isEnabled + ", modifiedDate="
				+ modifiedDate + "]";
	}

}
