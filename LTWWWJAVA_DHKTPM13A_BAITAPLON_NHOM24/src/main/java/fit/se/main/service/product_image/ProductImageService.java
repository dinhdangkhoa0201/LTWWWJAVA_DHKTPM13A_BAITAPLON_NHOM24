package fit.se.main.service.product_image;

import java.util.List;

import fit.se.main.model.Product;
import fit.se.main.model.ProductImage;

public interface ProductImageService {
	public void addProductImage(ProductImage productImage);
	public List<ProductImage> findByProduct(Product product);
	public void updateProductImage(ProductImage productImage);
	public void deletProductImage(ProductImage productImage);
}
