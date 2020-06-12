package fit.se.main.dao.account.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.dao.account.AccountDAO;
import fit.se.main.model.Account;
import fit.se.main.repository.AccountRepository;

@Repository
public class AccountDAOImpl implements AccountDAO{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account create(Account entity) {
		return accountRepository.save(entity);
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
	public Optional<Account> findByAccountname(String accountName) {
		return accountRepository.findByAccountname(accountName);
	}

	@Override
	public Optional<Account> findByPhone(String phone) {
		return accountRepository.findByPhone(phone);
	}

	@Override
	public Account findById(int id) {
		return accountRepository.getOne(id);
	}

	@Override
	public Optional<String> findAllEmail() {
		return accountRepository.findAllEmail();
	}
}
