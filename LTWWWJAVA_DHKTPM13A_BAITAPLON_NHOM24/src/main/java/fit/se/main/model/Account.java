package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	@GeneratedValue(strategy = 	GenerationType.IDENTITY)
	@Column(name = "account_id", nullable = false)
	private Long accountId;

	@Column(name = "accountname", nullable = false)
	private String accountName;

	@Column(name = "birthday")
	private LocalDate birthday;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "password", nullable = false)
	private String password;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "account_role", joinColumns = {
			@JoinColumn(name = "account_id")},
			inverseJoinColumns = 
			@JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
	private List<VerifyAccount> verificationTokens;
	
	private boolean isEnabled;
	
	public Account(String userName, String email, String phone, String password) {
		this.accountName = userName;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Account(Long accountId) {
		this.accountId = accountId;
	}

	public Account() {
	}
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

	public Set<Role> addRole(Role role){
		if(this.roles == null) {
			this.roles = new HashSet<Role>();
		}
		roles.add(role);
		return roles;
	}

	@Override
	public String toString() {
		return "KhachHang [khachhangId=" + accountId + ", hoTen=" + accountName + ", ngaySinh=" + birthday + ", gioiTinh="
				+ gender + ", email=" + email + ", soDT=" + phone + ", matKhau=" + password + ", roles=" + roles
				+ ", verificationTokens=" + verificationTokens + ", isEnabled=" + isEnabled + "]";
	}
	
}
