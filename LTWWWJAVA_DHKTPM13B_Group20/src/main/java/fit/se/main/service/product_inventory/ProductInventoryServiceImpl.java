package fit.se.main.service.product_inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.dao.product_inventory.ProductInventoryDAO;
import fit.se.main.model.ProductInventory;

@Service
@Transactional
public class ProductInventoryServiceImpl implements ProductInventoryService{

	@Autowired
	private ProductInventoryDAO productInventoryDAO;
	@Override
	public void createProductInventory(ProductInventory productInventory) {
		productInventoryDAO.create(productInventory);
	}

	@Override
	public void updateProductInventory(ProductInventory productInventory) {
		productInventoryDAO.update(productInventory);
	}

	@Override
	public void deleteProductInventory(ProductInventory productInventory) {
		productInventoryDAO.delete(productInventory);
	}

	@Override
	public List<ProductInventory> findAll() {
		return productInventoryDAO.findAll();
	}

}
