package fit.se.main.service.sale_order_header;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.se.main.dao.sale_order_header.OrderDAO;
import fit.se.main.model.Account;
import fit.se.main.model.SaleOrderHeader;

@Service
public class SaleOrderHeaderServiceImpl implements SaleOrderHeaderService{

	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	public void createOrder(SaleOrderHeader order) {
		orderDAO.create(order);
	}

	@Override
	public SaleOrderHeader findOrderById(int id) {
		return orderDAO.findById(id);
	}

	@Override
	public List<SaleOrderHeader> findOrderByAccount(Account account) {
		return orderDAO.findByAccount(account);
	}

	@Override
	public List<SaleOrderHeader> findAll() {
		return orderDAO.findAll();
	}

	@Override
	public void deleteOrder(SaleOrderHeader order) {
		orderDAO.delete(order);
	}

	@Override
	public void updateOrder(SaleOrderHeader order) {
		orderDAO.update(order);
	}

}
