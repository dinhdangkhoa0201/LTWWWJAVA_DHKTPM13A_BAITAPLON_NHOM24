package fit.se.main.dao.sale_order_detail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.SaleOrderDetail;
import fit.se.main.model.SaleOrderHeader;
import fit.se.main.repository.SaleOrderDetailRepository;
import fit.se.main.repository.SaleOrderHeaderRepository;

@Repository
public class SaleOrderDetailDAOImpl implements SaleOrderDetailDAO{
	
	@Autowired
	private SaleOrderDetailRepository saleOrderDetail;
	
	@Override
	public SaleOrderDetail findById(int id) {
		return null;
	}

	@Override
	public List<SaleOrderDetail> findAll() {
		return saleOrderDetail.findAll();
	}

	@Override
	public SaleOrderDetail create(SaleOrderDetail entity) {
		return saleOrderDetail.save(entity);
	}

	@Override
	public SaleOrderDetail update(SaleOrderDetail entity) {
		return saleOrderDetail.save(entity);
	}

	@Override
	public void delete(SaleOrderDetail entity) {
		saleOrderDetail.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SaleOrderDetail> findByOrder(SaleOrderHeader order) {
		return saleOrderDetail.findByOrder(order);
	}

}
