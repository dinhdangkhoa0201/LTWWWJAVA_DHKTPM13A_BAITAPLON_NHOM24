package fit.se.main.dao.product;

import java.util.List;
import java.util.Optional;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Supplier;

public interface ProductDAO extends IOperations<Product>{
	List<Product> findByCategory(Category category);
	List<Product> findBySupplier(Supplier supplier);
	List<Product> findByPrice(double pricemin, double pricemax);
	Integer quanityByCategory(int category_id);
	Integer quanityBySupplier(int supplier_id);
	Integer quanityByProduct(int product_id);
	List<Integer> findByNoSale();
	List<Integer> findByLowSale();
	List<Integer> findByMediumSale();
	List<Integer> findByHighSale();
	List<Integer> findByTopSale();
	List<Product> findByYear();
	List<Product> findByMonth();
	List<Product> findByWeek();
}
