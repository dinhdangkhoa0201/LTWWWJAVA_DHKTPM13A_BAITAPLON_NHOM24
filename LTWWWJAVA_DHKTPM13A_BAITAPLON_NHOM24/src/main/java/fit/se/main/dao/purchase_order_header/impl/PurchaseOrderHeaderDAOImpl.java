package fit.se.main.dao.purchase_order_header.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.dao.purchase_order_header.PurchaseOrderHeaderDAO;
import fit.se.main.model.PurchaseOrderHeader;
import fit.se.main.model.Supplier;
import fit.se.main.repository.PurchaseOrderHeaderRepository;

@Repository
public class PurchaseOrderHeaderDAOImpl implements PurchaseOrderHeaderDAO{

	@Autowired
	private PurchaseOrderHeaderRepository purchaseOrderHeaderRepository;
	@Override
	public PurchaseOrderHeader findById(int id) {
		return purchaseOrderHeaderRepository.getOne(id);
	}

	@Override
	public PurchaseOrderHeader create(PurchaseOrderHeader entity) {
		return purchaseOrderHeaderRepository.save(entity);
	}

	@Override
	public PurchaseOrderHeader update(PurchaseOrderHeader entity) {
		return purchaseOrderHeaderRepository.saveAndFlush(entity);
	}

	@Override
	public void delete(PurchaseOrderHeader entity) {
		purchaseOrderHeaderRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		purchaseOrderHeaderRepository.deleteById(entityId);
	}

	@Override
	public List<PurchaseOrderHeader> findBySupplier(Supplier supplier) {
		return purchaseOrderHeaderRepository.findBySupplier(supplier);
	}

	@Override
	public List<PurchaseOrderHeader> findAll() {
		return purchaseOrderHeaderRepository.findAll();
	}

}
