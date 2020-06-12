package fit.se.main.dao.purchase_order_detail.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.dao.purchase_order_detail.PurchaseOrderDetailDAO;
import fit.se.main.model.PurchaseOrderDetail;
import fit.se.main.model.PurchaseOrderHeader;
import fit.se.main.repository.PurchaseOrderDetailRepository;

@Repository
public class PurchaseOrderDetailDAOImpl implements PurchaseOrderDetailDAO{

	@Autowired
	private PurchaseOrderDetailRepository purchaseOrderDetailRepository;
	@Override
	public PurchaseOrderDetail findById(int id) {
		return purchaseOrderDetailRepository.getOne(id);
	}

	@Override
	public List<PurchaseOrderDetail> findAll() {
		return purchaseOrderDetailRepository.findAll();
	}

	@Override
	public PurchaseOrderDetail create(PurchaseOrderDetail entity) {
		return purchaseOrderDetailRepository.save(entity);
	}

	@Override
	public PurchaseOrderDetail update(PurchaseOrderDetail entity) {
		return purchaseOrderDetailRepository.saveAndFlush(entity);
	}

	@Override
	public void delete(PurchaseOrderDetail entity) {
		purchaseOrderDetailRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		purchaseOrderDetailRepository.deleteById(entityId);
	}

	@Override
	public List<PurchaseOrderDetail> findByPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader) {
		return purchaseOrderDetailRepository.findByPurchaseOrderHeader(purchaseOrderHeader);
	}

}
