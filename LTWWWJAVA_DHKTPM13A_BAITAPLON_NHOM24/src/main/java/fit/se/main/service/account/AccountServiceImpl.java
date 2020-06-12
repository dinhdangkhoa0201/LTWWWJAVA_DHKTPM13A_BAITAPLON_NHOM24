package fit.se.main.service.account;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.dao.account.AccountDAO;
import fit.se.main.dao.verifyaccount.VerifyAccountDAO;
import fit.se.main.dto.AccountCreateDTO;
import fit.se.main.dto.VerifyCodeDTO;
import fit.se.main.mail.Mail;
import fit.se.main.mail.MailService;
import fit.se.main.model.Account;
import fit.se.main.model.Role;
import fit.se.main.model.VerifyAccount;
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
		String gender = dto.getGender();
		String birthday = dto.getBirthday();
		String email = dto.getEmail();
		String phone = dto.getPhone();
		String password = dto.getPassword();
		
		Account account = new Account();
		account.setAccountName(userName);
		account.setGender(gender);
		account.setBirthday(birthday);
		account.setEmail(email);
		account.setPhone(phone);
		account.setPassword(passwordEncoder.encode(password));
		account.setEnabled(false);
		
		Role roleMember = null;
		
		if(roleService.findByName("ROLE_MEMBER").isPresent()){
			roleMember = roleService.findByName("ROLE_MEMBER").get();
		} else {
			roleMember = new Role("ROLE_MEMBER", "You are a member");
			roleMember = roleService.create(roleMember);
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
		
		account.addRole(roleMember);;
		
		account = accountDAO.create(account);
		
		return account;
		
	}
	

	@Override
	public Account createAdmin(AccountCreateDTO dto) throws Exception {
		String email = dto.getEmail();
		String username = dto.getAccountName();
		String password = dto.getPassword();
		String phone = dto.getPhone();
		
		Account account = new Account();
		account.setEmail(email);
		account.setAccountName(username);
		account.setPassword(passwordEncoder.encode(password));
		account.setPhone(phone);
		
		Role roleAdmin = null;
		Role roleMember = null;
		
		if(roleService.findByName("ROLE_ADMIN").isPresent()) {
			roleAdmin = roleService.findByName("ROLE_ADMIN").get();
		} else {
			roleAdmin = new Role("ROLE_ADMIN", "Admin");
			roleAdmin = roleService.create(roleAdmin);
		}
		
		if(roleService.findByName("ROLE_MEMBER").isPresent()) {
			roleMember = roleService.findByName("ROLE_MEMBER").get();
		} else {
			roleMember = new Role("ROLE_MEMBER", "Member");
			roleMember = roleService.create(roleMember);
		}		
		String token = RandomUtil.generateRandomStringNumber(10).toUpperCase();
		
		VerifyAccount verifyAccount = new VerifyAccount();
		verifyAccount.setAccount(account);
		verifyAccount.setCreateOn(LocalDateTime.now());
		verifyAccount.setExpiredDataToken(5);
		verifyAccount.setToken(token);
		verifyAccountDAO.create(verifyAccount);
		
		account.setEnabled(true);

		account.addRole(roleAdmin);
		account.addRole(roleMember);;
		
		account = accountDAO.create(account);
		
		return account;
	}

	@Override
	@Transactional
	public Optional<Account> findByEmail(String email) {
		return accountDAO.findByEmail(email);
	}

	@Override
	public Optional<Account> findByAccountname(String accountName) {
		return accountDAO.findByAccountname(accountName);
	}

	@Override
	public Account findById(int accountId) {
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

	@Override
	public List<Account> findAll() {
		return accountDAO.findAll();
	}

	@Override
	public void updateAccount(Account account) throws Exception {
		accountDAO.update(account);
	}


	@Override
	public void deteleById(int id) {
		accountDAO.deleteById(id);
	}


	@Override
	public Optional<String> findAllEmail() {
		return accountDAO.findAllEmail();
	}

}
