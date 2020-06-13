package fit.se.main.dao.sale_order_detail;

import java.util.List;

import fit.se.main.dao.IOperations;
import fit.se.main.model.SaleOrderDetail;
import fit.se.main.model.SaleOrderHeader;

public interface OrderDetailDAO extends IOperations<SaleOrderDetail>{
	List<SaleOrderDetail> findByOrder(SaleOrderHeader order);
}
