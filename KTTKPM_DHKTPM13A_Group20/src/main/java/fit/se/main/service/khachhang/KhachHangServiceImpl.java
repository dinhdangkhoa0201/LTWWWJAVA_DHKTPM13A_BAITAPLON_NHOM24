package fit.se.main.service.khachhang;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fit.se.main.dao.khachhang.KhachHangDAO;
import fit.se.main.dao.verifyaccount.VerifyAccountDAO;
import fit.se.main.dto.KhachHangCreateDTO;
import fit.se.main.dto.VerifyCodeDTO;
import fit.se.main.mail.Mail;
import fit.se.main.mail.MailService;
import fit.se.main.model.KhachHang;
import fit.se.main.model.Role;
import fit.se.main.model.VerifyAccount;
import fit.se.main.repository.KhachHangRepository;
import fit.se.main.service.role.RoleService;
import fit.se.main.util.RandomUtil;

@Service
public class KhachHangServiceImpl implements KhachHangService{
	
	@Autowired
	private KhachHangDAO khachHangDAO;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private VerifyAccountDAO verifyAccountDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public KhachHang createMember(KhachHangCreateDTO dto) throws Exception {
		String hoten = dto.getUsername();
		String email = dto.getEmail();
		String phone = dto.getPhone();
		String password = dto.getPassword();
		
		KhachHang khachHang = new KhachHang();
		khachHang.setHoTen(hoten);
		khachHang.setEmail(email);
		khachHang.setSoDT(phone);
		khachHang.setMatKhau(password);
		khachHang.setEnabled(false);
		
		if(roleService.findByName("ROLE_MEMBER").isPresent()){
			Role role = roleService.findByName("ROLE_MEMBER").get();
			khachHang.addRole(role);
		}
		
		String token = RandomUtil.generateRandomStringNumber(6).toUpperCase();
		
		VerifyAccount verifyAccount = new VerifyAccount();
		verifyAccount.setKhachHang(khachHang);
		verifyAccount.setCreateOn(LocalDateTime.now());
		verifyAccount.setExpiredDataToken(5);
		verifyAccount.setToken(token);
		verifyAccountDAO.create(verifyAccount);
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("khachhang", khachHang);
		maps.put("token", token);
		
		Mail mail = new Mail();
		mail.setFrom("hkvtravel@gmail.com");
		mail.setSubject("Registration");
		mail.setModel(maps);
		mailService.sendMail(mail);
		return khachHangDAO.create(khachHang);
	}

	@Override
	public KhachHang createAdmin(KhachHangCreateDTO dto) throws Exception {
		String email = dto.getEmail();
		String username = dto.getUsername();
		String password = dto.getPassword();
		String phone = dto.getPhone();
		
		KhachHang khachHang = new KhachHang();
		khachHang.setEmail(email);
		khachHang.setHoTen(username);
		khachHang.setMatKhau(password);
		khachHang.setSoDT(phone);
		
		if(roleService.findByName("ROLE_ADMIN").isPresent()) {
			Role role = roleService.findByName("ROLE_ADMIN").get();
			khachHang.addRole(role);
		}
		
		return khachHangDAO.create(khachHang);
	}
	
	@Override
	public Optional<KhachHang> findByEmailOrHoTen(String hoTen, String email) {
		return khachHangDAO.findByHotenOrEmail(hoTen, email);
	}

	@Override
	public Optional<KhachHang> findByEmail(String email) {
		return khachHangDAO.findByEmail(email);
	}

	@Override
	public Optional<KhachHang> findByHoTen(String hoten) {
		return khachHangDAO.findByHoten(hoten);
	}

	@Override
	public Optional<KhachHang> findById(Long khachhangId) {
		return khachHangDAO.findById(khachhangId);
	}

	@Override
	public Optional<KhachHang> findByPhone(String sodienthoai) {
		return khachHangDAO.findByPhone(sodienthoai);
	}


	@Override
	public void verifyCode(VerifyCodeDTO dto) {
		String token = dto.getToken();
		
		VerifyAccount verifyAccount = verifyAccountDAO.findByToken(token).get();
		KhachHang khachHang = verifyAccount.getKhachHang();
		khachHang.setEnabled(true);
		khachHangDAO.update(khachHang);
	}

}
