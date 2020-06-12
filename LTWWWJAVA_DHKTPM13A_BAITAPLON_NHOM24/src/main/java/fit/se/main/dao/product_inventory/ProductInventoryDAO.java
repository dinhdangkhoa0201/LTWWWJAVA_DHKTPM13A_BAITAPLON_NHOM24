package fit.se.main.dao.product_inventory;

import java.util.List;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Location;
import fit.se.main.model.ProductInventory;

public interface ProductInventoryDAO extends IOperations<ProductInventory>{
	List<ProductInventory> findByLocation(Location location);
}
