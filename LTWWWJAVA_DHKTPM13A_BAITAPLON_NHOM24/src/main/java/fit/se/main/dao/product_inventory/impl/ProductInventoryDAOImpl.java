package fit.se.main.dao.product_inventory.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.dao.product_inventory.ProductInventoryDAO;
import fit.se.main.model.Location;
import fit.se.main.model.ProductInventory;
import fit.se.main.repository.ProductInventoryRepository;

@Repository
public class ProductInventoryDAOImpl implements ProductInventoryDAO{
	@Autowired
	private ProductInventoryRepository productInventoryRepository;
	@Override
	public ProductInventory findById(int id) {
		return productInventoryRepository.getOne(id);
	}

	@Override
	public List<ProductInventory> findAll() {
		return productInventoryRepository.findAll();
	}

	@Override
	public ProductInventory create(ProductInventory entity) {
		return productInventoryRepository.save(entity);
	}

	@Override
	public ProductInventory update(ProductInventory entity) {
		return productInventoryRepository.saveAndFlush(entity);
	}

	@Override
	public void delete(ProductInventory entity) {
		productInventoryRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		productInventoryRepository.deleteById(entityId);
	}

	@Override
	public List<ProductInventory> findByLocation(Location location) {
		return productInventoryRepository.findByLocation(location);
	}

}
