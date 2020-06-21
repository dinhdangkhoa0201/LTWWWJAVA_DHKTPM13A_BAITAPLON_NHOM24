package fit.se.main.dao.account;

import java.util.Optional;

import org.springframework.stereotype.Component;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Account;

public interface AccountDAO extends IOperations<Account>{
	
	Optional<Account> findByEmail(String email);
	
	Optional<Account> findByPhone(String phone);

	Optional<String> findAllEmail();
	
	
}
