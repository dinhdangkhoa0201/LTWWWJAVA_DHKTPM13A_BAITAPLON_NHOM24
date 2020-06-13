package fit.se.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.SaleOrderDetail;
import fit.se.main.model.SaleOrderHeader;

public interface SaleOrderDetailRepository extends JpaRepository<SaleOrderDetail, Integer>{
	List<SaleOrderDetail> findByOrder(SaleOrderHeader order);
}
