package fit.se.main.dao.sale_order_detail.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.dao.sale_order_detail.OrderDetailDAO;
import fit.se.main.model.SaleOrderDetail;
import fit.se.main.model.SaleOrderHeader;
import fit.se.main.repository.SaleOrderDetailRepository;

@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO{
	@Autowired
	private SaleOrderDetailRepository orderDetailRepository;
	
	@Override
	public SaleOrderDetail findById(int id) {
		return orderDetailRepository.getOne(id);
	}

	@Override
	public List<SaleOrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public SaleOrderDetail create(SaleOrderDetail entity) {
		return orderDetailRepository.save(entity);
	}

	@Override
	public SaleOrderDetail update(SaleOrderDetail entity) {
		return orderDetailRepository.saveAndFlush(entity);
	}

	@Override
	public void delete(SaleOrderDetail entity) {
		orderDetailRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SaleOrderDetail> findByOrder(SaleOrderHeader order) {
		return orderDetailRepository.findByOrder(order);
	}

}
