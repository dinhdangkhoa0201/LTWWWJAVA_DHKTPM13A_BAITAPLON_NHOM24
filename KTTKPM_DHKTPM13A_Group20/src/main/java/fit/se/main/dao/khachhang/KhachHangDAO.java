package fit.se.main.dao.khachhang;

import java.util.Optional;

import fit.se.main.dao.IOperations;
import fit.se.main.model.KhachHang;

public interface KhachHangDAO extends IOperations<KhachHang>{
	Optional<KhachHang> findByHotenOrEmail(String hoten, String email);
	
	Optional<KhachHang> findByEmail(String email);
	
	Optional<KhachHang> findByHoten(String hoten);
	
	Optional<KhachHang> findByPhone(String soDT);
}
