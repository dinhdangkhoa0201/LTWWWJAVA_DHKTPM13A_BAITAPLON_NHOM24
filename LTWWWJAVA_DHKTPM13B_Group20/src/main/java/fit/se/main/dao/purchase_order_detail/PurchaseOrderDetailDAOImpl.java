package fit.se.main.dao.purchase_order_detail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.PurchaseOrderDetail;
import fit.se.main.model.PurchaseOrderHeader;

@Repository
public class PurchaseOrderDetailDAOImpl implements PurchaseOrderDetailDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public PurchaseOrderDetail findById(int id) {
		return em.find(PurchaseOrderDetail.class, id);
	}

	@Override
	public List<PurchaseOrderDetail> findAll() {
		return em.createNamedQuery("findAllPurchaseOrderDetail", PurchaseOrderDetail.class).getResultList();
	}

	@Override
	public PurchaseOrderDetail create(PurchaseOrderDetail entity) {
		em.persist(entity);
		em.flush();
		return null;
	}

	@Override
	public PurchaseOrderDetail update(PurchaseOrderDetail entity) {
		em.merge(entity);
		em.flush();
		return null;
	}

	@Override
	public void delete(PurchaseOrderDetail entity) {
	}

	@Override
	public void deleteById(int entityId) {
	}

	@Override
	public List<PurchaseOrderDetail> findByPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader) {
		return em.createNamedQuery("findPurchaseOrderDetailByPurchaseOrderHeader", PurchaseOrderDetail.class).setParameter("purchaseOrderHeader", purchaseOrderHeader).getResultList();
	}

}
