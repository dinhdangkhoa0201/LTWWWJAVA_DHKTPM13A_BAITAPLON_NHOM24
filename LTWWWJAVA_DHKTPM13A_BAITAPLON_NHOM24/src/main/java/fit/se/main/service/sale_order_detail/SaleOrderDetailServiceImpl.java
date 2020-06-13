package fit.se.main.service.sale_order_detail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.se.main.dao.sale_order_detail.OrderDetailDAO;
import fit.se.main.model.SaleOrderDetail;
import fit.se.main.model.SaleOrderHeader;

@Service
public class SaleOrderDetailServiceImpl implements SaleOrderDetailService{

	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Override
	public SaleOrderDetail createOrderDetail(SaleOrderDetail orderDetail) {
		return orderDetailDAO.create(orderDetail);
	}

	@Override
	public void updateOrderDetail(SaleOrderDetail orderDetail) {
		orderDetailDAO.update(orderDetail);
	}

	@Override
	public void deletOrderDetail(SaleOrderDetail orderDetail) {
		orderDetailDAO.delete(orderDetail);
	}

	@Override
	public List<SaleOrderDetail> findAll() {
		return orderDetailDAO.findAll();
	}

	@Override
	public List<SaleOrderDetail> findByOrder(SaleOrderHeader order) {
		return orderDetailDAO.findByOrder(order);
	}

}
