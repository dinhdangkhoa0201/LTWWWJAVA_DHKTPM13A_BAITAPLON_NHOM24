package fit.se.main.dao.purchase_order_detail;

import java.util.List;

import fit.se.main.dao.IOperations;
import fit.se.main.model.PurchaseOrderDetail;
import fit.se.main.model.PurchaseOrderHeader;

public interface PurchaseOrderDetailDAO extends IOperations<PurchaseOrderDetail>{
	List<PurchaseOrderDetail> findByPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader);
}
