package fit.se.main.dao.purchase_order_header;

import java.util.List;

import fit.se.main.dao.IOperations;
import fit.se.main.model.PurchaseOrderHeader;
import fit.se.main.model.Supplier;

public interface PurchaseOrderHeaderDAO extends IOperations<PurchaseOrderHeader>{
	public List<PurchaseOrderHeader> findBySupplier(Supplier supplier);
}
