package fit.se.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fit.se.main.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	
//	@Query(value = "select account a from account join fetch a.email", nativeQuery = true)
	Optional<Account> findByEmail(String email);
	
	@Query(value = "select * from account where accountname = ?1", nativeQuery = true)
	Optional<Account> findByAccountname(String accountName);
	
	Optional<Account> findByPhone(String phone);
	
	@Query(value = "select a.email from account a", nativeQuery = true)
	Optional<String> findAllEmail();
	
}
