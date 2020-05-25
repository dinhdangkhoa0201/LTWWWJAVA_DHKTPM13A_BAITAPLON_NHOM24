package fit.se.main.config;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fit.se.main.dao.role.RoleDAO;
import fit.se.main.dto.KhachHangCreateDTO;
import fit.se.main.model.KhachHang;
import fit.se.main.model.Role;
import fit.se.main.repository.KhachHangRepository;
import fit.se.main.repository.RoleRepository;
import fit.se.main.service.khachhang.KhachHangService;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	private KhachHangService khachHangService;
	
	@Autowired 
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event){
		if(roleRepository.findByName("ROLE_ADMIN") == null) {
			roleDAO.create(new Role("ROLE_ADMIN"));
		}
		
		if(roleRepository.findByName("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}
		
		if(khachHangService.findByEmail("manager@gmail.com") == null) {
			KhachHangCreateDTO admin = new KhachHangCreateDTO();
			admin.setUsername("admin");
			admin.setEmail("manager@gmail.com");
			admin.setPhone("0398122533");
			admin.setPassword(passwordEncoder.encode("123456"));
			
			try {
				khachHangService.createAdmin(admin);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(khachHangService.findByEmail("custumer@gmail.com") == null) {
			KhachHangCreateDTO member = new KhachHangCreateDTO();
			member.setUsername("custumer");
			member.setEmail("custumer@gmail.com");
			member.setPhone("0937602105");
			member.setPassword(passwordEncoder.encode("123456"));
			
			try {
				khachHangService.createMember(member);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
