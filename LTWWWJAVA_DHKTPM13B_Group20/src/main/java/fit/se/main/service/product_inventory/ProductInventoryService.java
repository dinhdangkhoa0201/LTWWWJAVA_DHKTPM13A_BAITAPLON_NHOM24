package fit.se.main.service.product_inventory;

import java.util.List;

import fit.se.main.model.ProductInventory;

public interface ProductInventoryService {
	public void createProductInventory(ProductInventory productInventory);
	public void updateProductInventory(ProductInventory productInventory);
	public void deleteProductInventory(ProductInventory productInventory);
	public List<ProductInventory> findAll();
}
