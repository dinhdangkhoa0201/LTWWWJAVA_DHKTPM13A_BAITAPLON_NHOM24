package fit.se.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fit.se.main.model.VerifyAccount;

public interface VerifyAccountRepository extends JpaRepository<VerifyAccount, Long>{
	Optional<VerifyAccount> findByToken(String token);
}
