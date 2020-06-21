package fit.se.main.service.purchase_order_header;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.dao.purchase_order_header.PurchaseOrderHeaderDAO;
import fit.se.main.model.PurchaseOrderHeader;
import fit.se.main.model.Supplier;

@Service
@Transactional
public class PurchaseOrderHeaderServiceImpl implements PurchaseOrderHeaderService{

	@Autowired
	private PurchaseOrderHeaderDAO purchaseOrderHeaderDAO;
	
	@Override
	public void createOrder(PurchaseOrderHeader order) {
		purchaseOrderHeaderDAO.create(order);
	}

	@Override
	public void deleteOrder(PurchaseOrderHeader order) {
		purchaseOrderHeaderDAO.delete(order);
	}

	@Override
	public void updateOrder(PurchaseOrderHeader order) {
		purchaseOrderHeaderDAO.update(order);
	}

	@Override
	public PurchaseOrderHeader findOrderById(int id) {
		return purchaseOrderHeaderDAO.findById(id);
	}

	@Override
	public List<PurchaseOrderHeader> findAll() {
		return purchaseOrderHeaderDAO.findAll();
	}

}
