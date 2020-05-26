package fit.se.main.service.account;

import java.util.Optional;

import fit.se.main.dto.AccountCreateDTO;
import fit.se.main.dto.VerifyCodeDTO;
import fit.se.main.model.Account;

public interface AccountService {
	public Account createMember(AccountCreateDTO dto) throws Exception;
	
	public Account createAdmin(AccountCreateDTO dto) throws Exception;
	
	Optional<Account> findByEmail(String email);
	
	Optional<Account> findByAccountname(String accountName);
	
	Optional<Account> findById(Long accountId);
	
	Optional<Account> findByPhone(String phone);
	
	public void verifyCode(VerifyCodeDTO dto);
}
