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
	private AccountRepository khachHangRepository;
	@Override
	public Optional<Account> findById(long id) {
		return khachHangRepository.findById(id);
	}

	@Override
	public List<Account> findAll() {
		return khachHangRepository.findAll();
	}

	@Override
	public Account create(Account entity) {
		return khachHangRepository.save(entity);
	}

	@Override
	public Account update(Account entity) {
		return khachHangRepository.save(entity);
	}

	@Override
	public void delete(Account entity) {
		khachHangRepository.delete(entity);
	}

	@Override
	public void deleteById(long entityId) {
		khachHangRepository.deleteById(entityId);
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		return khachHangRepository.findByEmail(email);
	}

	@Override
	public Optional<Account> findByHoten(String accountName) {
		return khachHangRepository.findByAccountname(accountName);
	}

	@Override
	public Optional<Account> findByPhone(String phone) {
		return khachHangRepository.findByPhone(phone);
	}
}
