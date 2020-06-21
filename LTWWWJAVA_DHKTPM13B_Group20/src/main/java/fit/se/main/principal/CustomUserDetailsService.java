package fit.se.main.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fit.se.main.model.Account;
import fit.se.main.service.account.AccountService;

@Service
@Configurable
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		System.out.println("account service : " + accountService);
		System.out.println("User name " + username);
		
		Account khachHang = accountService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));
		System.out.println("Dang Nhap , Account : " + khachHang);
		if(khachHang != null) {
			return AccountPricipal.create(khachHang);
		}
		
		throw new UsernameNotFoundException("User '" + username +"'not found");
	}
	
}
