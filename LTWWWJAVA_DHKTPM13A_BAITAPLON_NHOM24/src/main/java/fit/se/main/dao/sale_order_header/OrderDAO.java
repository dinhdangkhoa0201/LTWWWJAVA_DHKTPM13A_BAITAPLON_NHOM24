package fit.se.main.dao.sale_order_header;

import java.util.List;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Account;
import fit.se.main.model.SaleOrderHeader;

public interface OrderDAO extends IOperations<SaleOrderHeader>{
	List<SaleOrderHeader> findByAccount(Account account);
}
