package fit.se.main.service.sale_order_header;

import java.util.List;

import fit.se.main.model.Account;
import fit.se.main.model.SaleOrderHeader;

public interface SaleOrderHeaderService {
	public void createOrder(SaleOrderHeader order);
	public void deleteOrder(SaleOrderHeader order);
	public void updateOrder(SaleOrderHeader order);
	public SaleOrderHeader findOrderById(int id);
	public List<SaleOrderHeader> findOrderByAccount(Account account);
	public List<SaleOrderHeader> findAll();
}
