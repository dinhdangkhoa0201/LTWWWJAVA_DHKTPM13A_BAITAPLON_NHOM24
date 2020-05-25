package fit.se.main.principal;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fit.se.main.model.KhachHang;

public class UserPricipal implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long khachhangID;
	
	private String hoTen;
	
	private LocalDate ngaySinh;
	
	private String gioiTinh;
	
	private String email;
	
	private String matKhau;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPricipal() {
	
	}

	public UserPricipal(Long khachhangID, String hoTen, LocalDate ngaySinh, String gioiTinh, String email,
			String matKhau, Collection<? extends GrantedAuthority> authorities) {
		this.khachhangID = khachhangID;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.matKhau = matKhau;
		this.authorities = authorities;
	}
	
	public static UserDetails create(KhachHang khachHang) {
		List<GrantedAuthority> authorities = khachHang.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return new UserPricipal(khachHang.getKhachhangId(), khachHang.getHoTen(), khachHang.getNgaySinh(), khachHang.getGioiTinh(), khachHang.getEmail(), khachHang.getMatKhau(), authorities);
	}

	public Long getKhachhangID() {
		return khachhangID;
	}

	public void setKhachhangID(Long khachhangID) {
		this.khachhangID = khachhangID;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return matKhau;
	}

	@Override
	public String getUsername() {
		return hoTen;
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
		return Objects.hash(khachhangID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPricipal other = (UserPricipal) obj;
		if (khachhangID == null) {
			if (other.khachhangID != null)
				return false;
		} else if (!khachhangID.equals(other.khachhangID))
			return false;
		return true;
	}
	
	
}
