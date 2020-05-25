package fit.se.main.service.khachhang;

import java.util.Optional;

import fit.se.main.dto.KhachHangCreateDTO;
import fit.se.main.dto.VerifyCodeDTO;
import fit.se.main.model.KhachHang;

public interface KhachHangService {
	public KhachHang createMember(KhachHangCreateDTO dto) throws Exception;
	
	public KhachHang createAdmin(KhachHangCreateDTO dto) throws Exception;
	
	Optional<KhachHang> findByEmailOrHoTen(String hoTen, String email);
	
	Optional<KhachHang> findByEmail(String email);
	
	Optional<KhachHang> findByHoTen(String hoten);
	
	Optional<KhachHang> findById(Long khachhangId);
	
	Optional<KhachHang> findByPhone(String sodienthoai);
	
	public void verifyCode(VerifyCodeDTO dto);
}
