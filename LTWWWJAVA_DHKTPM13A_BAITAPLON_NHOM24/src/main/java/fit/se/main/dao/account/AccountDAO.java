package fit.se.main.dao.account;

import java.util.Optional;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Account;

public interface AccountDAO extends IOperations<Account>{
	
	Optional<Account> findByEmail(String email);
	
	Optional<Account> findByHoten(String accountname);
	
	Optional<Account> findByPhone(String phone);
}
