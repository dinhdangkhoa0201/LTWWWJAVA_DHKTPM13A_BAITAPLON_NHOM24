package fit.se.main.service.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import fit.se.main.dto.AccountCreateDTO;
import fit.se.main.dto.VerifyCodeDTO;
import fit.se.main.model.Account;

public interface AccountService {
	public Account createMember(AccountCreateDTO dto) throws Exception;
	
	public Account createAdmin(AccountCreateDTO dto) throws Exception;
	
	List<Account> findAll();
	
	public void updateAccount(Account account) throws Exception;
	

	Optional<Account> findByEmail(String email);
	
	Account findById(int accountId);
	
	Optional<Account> findByPhone(String phone);
	
	public void verifyCode(VerifyCodeDTO dto);
	
	public void deteleById(int id);
	
	Optional<String> findAllEmail();
}
