package fit.se.main.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fit.se.main.model.KhachHang;
import fit.se.main.service.khachhang.KhachHangService;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private KhachHangService khachHangService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		KhachHang khachHang = khachHangService.findByEmailOrHoTen(username, username).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));
		if(khachHang != null) {
			return UserPricipal.create(khachHang);
		}
		
		throw new UsernameNotFoundException("User '" + username +"'not found");
	}

}
