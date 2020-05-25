package fit.se.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fit.se.main.model.KhachHang;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long>{
	Optional<KhachHang> findByEmail(String email);
	Optional<KhachHang> findByHoTen(String hoten);
	
	@Query(value = "select * from khachhang where hoten = ?0 || email = ?1", nativeQuery = true)
	Optional<KhachHang> findByHotenOrEmail(String hoten, String email);
	Optional<KhachHang> findBySoDT(String sodienthoai);
	@Query(value = "select * from khachhang where hoten = ?0", nativeQuery = true)
	List<KhachHang> findByNameContaining(String hoten);
	
//	@Query(value = "select * form khachhang where email = ?0", nativeQuery = true)
//	KhachHang findByEmail(String email);
	
}
