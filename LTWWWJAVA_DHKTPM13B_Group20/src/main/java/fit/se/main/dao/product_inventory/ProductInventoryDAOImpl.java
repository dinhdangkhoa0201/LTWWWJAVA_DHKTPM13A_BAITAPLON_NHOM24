package fit.se.main.dao.product_inventory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.Location;
import fit.se.main.model.ProductInventory;
import fit.se.main.repository.ProductInventoryRepository;

@Repository
public class ProductInventoryDAOImpl implements ProductInventoryDAO{
	
	@Autowired
	private ProductInventoryRepository productInventory;
	
	@Override
	public ProductInventory findById(int id) {
		return null;
	}

	@Override
	public List<ProductInventory> findAll() {
		return productInventory.findAll();
	}

	@Override
	public ProductInventory create(ProductInventory entity) {
		return productInventory.save(entity);
	}

	@Override
	public ProductInventory update(ProductInventory entity) {
		return productInventory.save(entity);
	}

	@Override
	public void delete(ProductInventory entity) {
		productInventory.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		
	}

	@Override
	public List<ProductInventory> findByLocation(Location location) {
		return productInventory.findByLocation(location);
	}

}
