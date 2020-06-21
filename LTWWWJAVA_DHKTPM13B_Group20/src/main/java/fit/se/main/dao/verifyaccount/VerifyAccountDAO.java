package fit.se.main.dao.verifyaccount;

import java.util.Optional;

import fit.se.main.model.VerifyAccount;

public interface VerifyAccountDAO {
	VerifyAccount create(VerifyAccount verifyAccount);
	VerifyAccount findByToken(String token);
	VerifyAccount findById(int id);
}
