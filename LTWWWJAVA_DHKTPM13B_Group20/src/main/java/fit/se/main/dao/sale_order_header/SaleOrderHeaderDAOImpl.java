package fit.se.main.dao.sale_order_header;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.Account;
import fit.se.main.model.SaleOrderHeader;
import fit.se.main.repository.SaleOrderHeaderRepository;

@Repository
public class SaleOrderHeaderDAOImpl implements SaleOrderHeaderDAO{
	
	@Autowired
	private SaleOrderHeaderRepository saleOrderHeaderRepository;
	
	@Override
	public SaleOrderHeader findById(int id) {
		return saleOrderHeaderRepository.getOne(id);
	}

	@Override
	public List<SaleOrderHeader> findAll() {
		return saleOrderHeaderRepository.findAll();
	}

	@Override
	public SaleOrderHeader create(SaleOrderHeader entity) {
		return saleOrderHeaderRepository.save(entity);
	}

	@Override
	public SaleOrderHeader update(SaleOrderHeader entity) {
		return saleOrderHeaderRepository.save(entity);
	}

	@Override
	public void delete(SaleOrderHeader entity) {
		saleOrderHeaderRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		saleOrderHeaderRepository.deleteById(entityId);
	}

	@Override
	public List<SaleOrderHeader> findByAccount(Account account) {
		return saleOrderHeaderRepository.findByAccount(account);
	}


	
}
