package fit.se.main.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fit.se.main.model.Account;
import fit.se.main.service.account.AccountService;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private AccountService khachHangService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account khachHang = khachHangService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));
		System.out.println("Dang Nhap , Account : " + khachHang);
		if(khachHang != null) {
			return AccountPricipal.create(khachHang);
		}
		
		throw new UsernameNotFoundException("User '" + username +"'not found");
	}

}
