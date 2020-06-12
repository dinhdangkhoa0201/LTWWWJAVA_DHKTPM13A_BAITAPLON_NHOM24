package fit.se.main.service.purchase_order_header;

import java.util.List;

import fit.se.main.model.PurchaseOrderHeader;
import fit.se.main.model.Supplier;

public interface PurchaseOrderHeaderService {
	public void createOrder(PurchaseOrderHeader order);
	public void deleteOrder(PurchaseOrderHeader order);
	public void updateOrder(PurchaseOrderHeader order);
	public PurchaseOrderHeader findOrderById(int id);
	public List<PurchaseOrderHeader> findOrderBySupplier(Supplier supplier);
	public List<PurchaseOrderHeader> findAll();
}
