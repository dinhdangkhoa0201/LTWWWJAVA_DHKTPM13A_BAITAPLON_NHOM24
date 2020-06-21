package fit.se.main.service.purchase_order_detail;

import java.util.List;

import fit.se.main.model.PurchaseOrderDetail;
import fit.se.main.model.PurchaseOrderHeader;

public interface PurchaseOrderDetailService {
	public void createOrder(PurchaseOrderDetail order);
	public void deleteOrder(PurchaseOrderDetail order);
	public void updateOrder(PurchaseOrderDetail order);
	public PurchaseOrderDetail findOrderById(int id);
	public List<PurchaseOrderDetail> findOrderByPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader);
	public List<PurchaseOrderDetail> findAll();
}
