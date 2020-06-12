package fit.se.main.principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fit.se.main.model.Account;
import fit.se.main.model.Role;

public class AccountPricipal implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int accountId;
	
	private String accountName;
	
	private String birthday;
	
	private String gender;
	
	private String email;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public AccountPricipal() {
	
	}
	
	public AccountPricipal(int accountId, String accountName, String birthday, String gender, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetails create(Account account) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role : account.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
//		for(AccountRole accountRole : account.getAccountRoles()) {
//			authorities.add(new SimpleGrantedAuthority(accountRole.getRole().getName()));
//		}
		return new AccountPricipal(account.getAccountId(), account.getAccountName(), account.getBirthday(), account.getGender(), account.getEmail(), account.getPassword(), authorities);
	}
	
	public int getAccountId() {
		return accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return accountName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
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
		AccountPricipal other = (AccountPricipal) obj;
		if (accountId != other.accountId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountPricipal [accountId=" + accountId + ", accountName=" + accountName + ", birthday=" + birthday
				+ ", gender=" + gender + ", email=" + email + ", password=" + password + ", authorities=" + authorities
				+ "]";
	}
	
	
	
}
