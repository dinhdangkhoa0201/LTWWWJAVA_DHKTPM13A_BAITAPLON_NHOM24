package fit.se.main.dao.product;

import java.util.Optional;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Category;
import fit.se.main.model.Product;

public interface ProductDAO extends IOperations<Product>{
	Optional<Product> findByCategory(Category category);
	Optional<Product> findByPrice(double price);
}
