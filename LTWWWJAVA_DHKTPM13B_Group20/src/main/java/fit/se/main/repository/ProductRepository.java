package fit.se.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Supplier;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query(value = "select count(product_id) from product where category_id = ?1 group by category_id", nativeQuery = true)
	Integer quanityByCategory(int category_id);
	
	@Query(value = "select count(product_id) from product where supplier_id = ?1 group by supplier_id", nativeQuery = true)
	Integer quanityBySupplier(int supplier_id);
	
	@Query(value = "select sum(quantity) from sale_order_detail where product_id = ?1 group by product_id", nativeQuery = true)
	Integer quanityByProduct(int product_id);
	
	List<Product> findByCategory(Category category);
	List<Product> findBySupplier(Supplier Supplier);
	
	@Query(value = "select * from product where price >= ?1 and price <= ?2", nativeQuery = true)
	List<Product> findByPrice(double pricemin, double pricemax);
	
	@Query(value = "select product_id from dbo.product where product_id not in(select product_id from dbo.sale_order_detail)", nativeQuery = true)
	List<Integer> findByNoSale();
	
	@Query(value = "select product_id from sale_order_detail s group by s.product_id having sum(quantity) >0 and sum(quantity) <= 5", nativeQuery = true)
	List<Integer> findByLowSale();
	
	@Query(value = "select product_id from sale_order_detail s group by s.product_id having sum(quantity) >5 and sum(quantity) <= 10", nativeQuery = true)
	List<Integer> findByMediumSale();
	
	@Query(value = "select product_id from sale_order_detail s group by s.product_id having sum(quantity) >10 and sum(quantity) <= 15", nativeQuery = true)
	List<Integer> findByHighSale();
	
	@Query(value = "select product_id from sale_order_detail s group by s.product_id having sum(quantity) >15", nativeQuery = true)
	List<Integer> findByTopSale();
	
	@Query(value = "select * from dbo.product where YEAR(modified_date) = YEAR(GETDATE())- 1 or  YEAR(modified_date) = YEAR(GETDATE())", nativeQuery = true)
	List<Product> findByYear();
	
	@Query(value = "select * from dbo.product where MONTH(modified_date) = MONTH(GETDATE())- 1 or MONTH(modified_date) = MONTH(GETDATE())", nativeQuery = true)
	List<Product> findByMonth();
	
	@Query(value = "select * from dbo.product where datepart(WEEK,modified_date) = datepart(WEEK,GETDATE())- 1 or datepart(WEEK,modified_date) = datepart(WEEK,GETDATE())", nativeQuery = true)
	List<Product> findByWeek();
}
