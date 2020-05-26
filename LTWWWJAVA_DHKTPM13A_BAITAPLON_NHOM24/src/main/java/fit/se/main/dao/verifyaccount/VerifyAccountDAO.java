package fit.se.main.dao.verifyaccount;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import fit.se.main.model.VerifyAccount;

public interface VerifyAccountDAO {
	VerifyAccount create(VerifyAccount verifyAccount);
	Optional<VerifyAccount> findByToken(String token);
	Optional<VerifyAccount> findById(Long id);
}
