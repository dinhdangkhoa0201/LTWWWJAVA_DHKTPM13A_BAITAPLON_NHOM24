package fit.se.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fit.se.main.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	Optional<Account> findByEmail(String email);
	
	Optional<Account> findByPhone(String phone);
	
	@Query(value = "select a.email from account a", nativeQuery = true)
	Optional<String> findAllEmail();
}
