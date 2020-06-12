package fit.se.main.service.sale_order_detail;

import java.util.List;

import fit.se.main.model.SaleOrderHeader;
import fit.se.main.model.SaleOrderDetail;

public interface SaleOrderDetailService {
	public SaleOrderDetail createOrderDetail(SaleOrderDetail orderDetail);
	public void updateOrderDetail(SaleOrderDetail orderDetail);
	public void deletOrderDetail(SaleOrderDetail orderDetail);
	
	public List<SaleOrderDetail> findAll();
	public List<SaleOrderDetail> findByOrder(SaleOrderHeader order);
	
}
