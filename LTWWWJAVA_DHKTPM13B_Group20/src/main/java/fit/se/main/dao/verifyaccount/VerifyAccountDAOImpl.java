package fit.se.main.dao.verifyaccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.VerifyAccount;
import fit.se.main.repository.VerifyAccountRepository;

@Repository
public class VerifyAccountDAOImpl implements VerifyAccountDAO{
	
	@Autowired
	private VerifyAccountRepository verifyAccountRepository;
	
	@Override
	public VerifyAccount create(VerifyAccount verifyAccount) {
		return verifyAccountRepository.save(verifyAccount);
	}

	@Override
	public VerifyAccount findByToken(String token) {
		return verifyAccountRepository.findByToken(token);
	}

	@Override
	public VerifyAccount findById(int id) {
		return verifyAccountRepository.getOne(id);
	}

}
