package fit.se.main.dao.khachhang.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.dao.khachhang.KhachHangDAO;
import fit.se.main.model.KhachHang;
import fit.se.main.repository.KhachHangRepository;

@Repository
public class KhachHangDAOImpl implements KhachHangDAO{

	@Autowired
	private KhachHangRepository khachHangRepository;
	@Override
	public Optional<KhachHang> findById(long id) {
		return khachHangRepository.findById(id);
	}

	@Override
	public List<KhachHang> findAll() {
		return khachHangRepository.findAll();
	}

	@Override
	public KhachHang create(KhachHang entity) {
		return khachHangRepository.save(entity);
	}

	@Override
	public KhachHang update(KhachHang entity) {
		return khachHangRepository.save(entity);
	}

	@Override
	public void delete(KhachHang entity) {
		khachHangRepository.delete(entity);
	}

	@Override
	public void deleteById(long entityId) {
		khachHangRepository.deleteById(entityId);
	}

	@Override
	public Optional<KhachHang> findByHotenOrEmail(String hoten, String email) {
		return khachHangRepository.findByHotenOrEmail(hoten, email);
	}

	@Override
	public Optional<KhachHang> findByEmail(String email) {
		return khachHangRepository.findByEmail(email);
	}

	@Override
	public Optional<KhachHang> findByHoten(String hoten) {
		return khachHangRepository.findByHoTen(hoten);
	}

	@Override
	public Optional<KhachHang> findByPhone(String soDT) {
		return khachHangRepository.findBySoDT(soDT);
	}
}
