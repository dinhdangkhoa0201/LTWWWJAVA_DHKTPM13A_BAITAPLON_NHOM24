package fit.se.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.PurchaseOrderHeader;
import fit.se.main.model.Supplier;

public interface PurchaseOrderHeaderRepository extends JpaRepository<PurchaseOrderHeader, Integer>{
	List<PurchaseOrderHeader> findBySupplier(Supplier supplier);
}
