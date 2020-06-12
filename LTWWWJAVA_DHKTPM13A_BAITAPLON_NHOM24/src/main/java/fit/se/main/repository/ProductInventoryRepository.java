package fit.se.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.Location;
import fit.se.main.model.ProductInventory;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Integer>{
	List<ProductInventory> findByLocation(Location location);
}
