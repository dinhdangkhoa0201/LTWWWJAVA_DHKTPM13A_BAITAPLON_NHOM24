package fit.se.main.principal;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fit.se.main.model.Account;

public class AccountPricipal implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long accountId;
	
	private String accountName;
	
	private LocalDate birthday;
	
	private String gender;
	
	private String email;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public AccountPricipal() {
	
	}
	
	public AccountPricipal(Long accountId, String accountName, LocalDate birthday, String gender, String email, String password,
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
		List<GrantedAuthority> authorities = account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return new AccountPricipal(account.getAccountId(), account.getAccountName(), account.getBirthday(), account.getGender(), account.getEmail(), account.getPassword(), authorities);
	}

	

	public Long getAccountId() {
		return accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public LocalDate getBirthday() {
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
		return Objects.hash(accountId);
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
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		return true;
	}
	
	
}
