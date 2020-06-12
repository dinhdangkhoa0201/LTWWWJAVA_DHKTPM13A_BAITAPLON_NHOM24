package fit.se.main.dao.sale_order_header.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.dao.sale_order_header.OrderDAO;
import fit.se.main.model.Account;
import fit.se.main.model.SaleOrderHeader;
import fit.se.main.repository.SaleOrderHeaderRepository;

@Repository
public class OrderDAOImpl implements OrderDAO{
	
	@Autowired
	private SaleOrderHeaderRepository orderReponsitory;
	
	@Override
	public SaleOrderHeader findById(int id) {
		return orderReponsitory.getOne(id);
	}

	@Override
	public List<SaleOrderHeader> findAll() {
		return orderReponsitory.findAll();
	}

	@Override
	public SaleOrderHeader create(SaleOrderHeader entity) {
		return orderReponsitory.save(entity);
	}

	@Override
	public SaleOrderHeader update(SaleOrderHeader entity) {
		return orderReponsitory.saveAndFlush(entity);
	}

	@Override
	public void delete(SaleOrderHeader entity) {
		orderReponsitory.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		orderReponsitory.deleteById(entityId);
	}

	@Override
	public List<SaleOrderHeader> findByAccount(Account account) {
		return orderReponsitory.findByAccount(account);
	}


	
}
