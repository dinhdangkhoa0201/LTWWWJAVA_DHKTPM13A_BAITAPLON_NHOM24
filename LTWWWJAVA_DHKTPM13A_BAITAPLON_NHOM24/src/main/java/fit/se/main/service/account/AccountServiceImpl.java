package fit.se.main.service.account;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fit.se.main.dao.account.AccountDAO;
import fit.se.main.dao.verifyaccount.VerifyAccountDAO;
import fit.se.main.dto.AccountCreateDTO;
import fit.se.main.dto.VerifyCodeDTO;
import fit.se.main.mail.Mail;
import fit.se.main.mail.MailService;
import fit.se.main.model.Account;
import fit.se.main.model.Role;
import fit.se.main.model.VerifyAccount;
import fit.se.main.repository.AccountRepository;
import fit.se.main.service.role.RoleService;
import fit.se.main.util.RandomUtil;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private VerifyAccountDAO verifyAccountDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Account createMember(AccountCreateDTO dto) throws Exception {
		String userName = dto.getAccountName();
		String email = dto.getEmail();
		String phone = dto.getPhone();
		String password = dto.getPassword();
		
		Account account = new Account();
		account.setAccountName(userName);
		account.setEmail(email);
		account.setPhone(phone);
		account.setPassword(passwordEncoder.encode(password));
		account.setEnabled(false);
		
		if(roleService.findByName("ROLE_MEMBER").isPresent()){
			Role role = roleService.findByName("ROLE_MEMBER").get();
			account.addRole(role);
		} else {
			Role role = new Role("ROLE_MEMBER", "You are a member");
			roleService.create(role);
			account.addRole(role);
		}
		
		String token = RandomUtil.generateRandomStringNumber(10).toUpperCase();
		
		VerifyAccount verifyAccount = new VerifyAccount();
		verifyAccount.setAccount(account);
		verifyAccount.setCreateOn(LocalDateTime.now());
		verifyAccount.setExpiredDataToken(5);
		verifyAccount.setToken(token);
		verifyAccountDAO.create(verifyAccount);
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("account", account);
		maps.put("token", token);
		
		Mail mail = new Mail();
		mail.setFrom("managerhkv0201@gmail.com");
		mail.setTo(account.getEmail());
		mail.setSubject("Registration");
		mail.setModel(maps);
		mailService.sendMail(mail);
		
		System.out.println(account);
		return accountDAO.create(account);
		
	}

	@Override
	public Account createAdmin(AccountCreateDTO dto) throws Exception {
		String email = dto.getEmail();
		String username = dto.getAccountName();
		String password = dto.getPassword();
		String phone = dto.getPhone();
		
		Account khachHang = new Account();
		khachHang.setEmail(email);
		khachHang.setAccountName(username);;
		khachHang.setPassword(password);;
		khachHang.setPhone(phone);;
		
		if(roleService.findByName("ROLE_ADMIN").isPresent()) {
			Role role = roleService.findByName("ROLE_ADMIN").get();
			khachHang.addRole(role);
		}
		
		return accountDAO.create(khachHang);
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		return accountDAO.findByEmail(email);
	}

	@Override
	public Optional<Account> findByAccountname(String accountName) {
		return accountDAO.findByHoten(accountName);
	}

	@Override
	public Optional<Account> findById(Long accountId) {
		return accountDAO.findById(accountId);
	}

	@Override
	public Optional<Account> findByPhone(String phone) {
		return accountDAO.findByPhone(phone);
	}


	@Override
	public void verifyCode(VerifyCodeDTO dto) {
		String token = dto.getToken();
		VerifyAccount verifyAccount = verifyAccountDAO.findByToken(token).get();
		
		Account account = verifyAccount.getAccount();
		account.setEnabled(true);
		accountDAO.update(account);
	}

}
