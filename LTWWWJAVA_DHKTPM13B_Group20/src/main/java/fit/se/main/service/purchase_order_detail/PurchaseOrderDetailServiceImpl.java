package fit.se.main.service.purchase_order_detail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.dao.purchase_order_detail.PurchaseOrderDetailDAO;
import fit.se.main.model.PurchaseOrderDetail;
import fit.se.main.model.PurchaseOrderHeader;

@Service
@Transactional
public class PurchaseOrderDetailServiceImpl implements PurchaseOrderDetailService{

	@Autowired
	private PurchaseOrderDetailDAO purchaseOrderDetailDAO;;
	@Override
	public void createOrder(PurchaseOrderDetail order) {
		purchaseOrderDetailDAO.create(order);
	}

	@Override
	public void deleteOrder(PurchaseOrderDetail order) {
		purchaseOrderDetailDAO.delete(order);
	}

	@Override
	public void updateOrder(PurchaseOrderDetail order) {
		purchaseOrderDetailDAO.update(order);
	}

	@Override
	public PurchaseOrderDetail findOrderById(int id) {
		return purchaseOrderDetailDAO.findById(id);
	}

	@Override
	public List<PurchaseOrderDetail> findOrderByPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader) {
		return purchaseOrderDetailDAO.findByPurchaseOrderHeader(purchaseOrderHeader);
	}

	@Override
	public List<PurchaseOrderDetail> findAll() {
		return null;
	}

}
