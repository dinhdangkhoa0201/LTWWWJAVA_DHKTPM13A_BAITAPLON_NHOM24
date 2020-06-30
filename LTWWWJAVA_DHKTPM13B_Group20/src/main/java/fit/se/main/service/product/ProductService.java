package fit.se.main.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fit.se.main.dto.ProductCreateDTO;
import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Supplier;

public interface ProductService {
	public Product createProduct(ProductCreateDTO productCreateDTO) throws Exception;
	public Product createProduct(Product product) throws Exception;
	public void deleteProduct(Product product);
	public void deleteProductById(int productId);
	public void updateProduct(Product product);
	List<Product> findAll();
	List<Product> findByCategory(Category category);
	List<Product> findBySupplier(Supplier supplier);
	List<Product> findByPrice(double pricemin, double pricemax);
	Product findById(int productId);
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
<<<<<<< HEAD
=======
	public Page<Product> findPaginated(Pageable pageable, List<Product> products);
>>>>>>> vophan
}
