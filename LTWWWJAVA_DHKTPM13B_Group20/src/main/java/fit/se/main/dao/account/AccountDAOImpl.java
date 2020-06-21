package fit.se.main.dao.account;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.model.Account;
import fit.se.main.repository.AccountRepository;

@Repository
public class AccountDAOImpl implements AccountDAO{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account findById(int id) {
		return accountRepository.getOne(id);
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	@Transactional
	public Account create(Account enity) {
		return accountRepository.saveAndFlush(enity);
	}

	@Override
	public Account update(Account entity) {
		return accountRepository.save(entity);
	}

	@Override
	public void delete(Account entity) {
		accountRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		accountRepository.deleteById(entityId);
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}

	@Override
	public Optional<Account> findByPhone(String phone) {
		return accountRepository.findByPhone(phone);
	}

	@Override
	public Optional<String> findAllEmail() {
		return accountRepository.findAllEmail();
	}
}
