package fit.se.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.Account;
import fit.se.main.model.SaleOrderHeader;

public interface SaleOrderHeaderRepository extends JpaRepository<SaleOrderHeader, Integer>{
	List<SaleOrderHeader> findByAccount(Account account);
	
}
