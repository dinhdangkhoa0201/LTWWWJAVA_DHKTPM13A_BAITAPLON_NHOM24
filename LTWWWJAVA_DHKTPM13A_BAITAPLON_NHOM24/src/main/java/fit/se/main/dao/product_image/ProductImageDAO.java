package fit.se.main.dao.product_image;

import java.util.List;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Product;
import fit.se.main.model.ProductImage;

public interface ProductImageDAO extends IOperations<ProductImage>{
	List<ProductImage> findByProduct(Product product);
}
