package fit.se.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.VerifyAccount;

public interface VerifyAccountRepository extends JpaRepository<VerifyAccount, Integer>{
	Optional<VerifyAccount> findByToken(String token);
}
