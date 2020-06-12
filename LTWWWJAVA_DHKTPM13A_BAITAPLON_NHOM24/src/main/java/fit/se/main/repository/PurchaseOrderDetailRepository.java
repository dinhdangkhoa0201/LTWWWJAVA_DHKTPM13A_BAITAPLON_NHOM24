package fit.se.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.PurchaseOrderHeader;
import fit.se.main.model.PurchaseOrderDetail;

public interface PurchaseOrderDetailRepository extends JpaRepository<PurchaseOrderDetail, Integer>{
	List<PurchaseOrderDetail> findByPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader);
}	
