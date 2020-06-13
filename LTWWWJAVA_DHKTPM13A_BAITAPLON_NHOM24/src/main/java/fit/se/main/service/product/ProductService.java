package fit.se.main.service.product;

import java.util.List;
import java.util.Optional;

import fit.se.main.dto.ProductCreateDTO;
import fit.se.main.model.Category;
import fit.se.main.model.Product;

public interface ProductService {
	public Product createProduct(ProductCreateDTO productCreateDTO) throws Exception;
	public Product createProduct(Product product) throws Exception;
	public void deleteProduct(Product product);
	public void deleteProductById(int productId);
	public void updateProduct(Product product);
	List<Product> findAll();
	Optional<Product> findByCategory(Category category);
	Product findById(int productId);
}
