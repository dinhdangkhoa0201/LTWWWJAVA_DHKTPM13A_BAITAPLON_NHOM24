package fit.se.main.service.product_image;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.dao.product_image.ProductImageDAO;
import fit.se.main.model.Product;
import fit.se.main.model.ProductImage;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService{

	@Autowired
	private ProductImageDAO productImageDAO;
	@Override
	public void addProductImage(ProductImage productImage) {
		productImageDAO.create(productImage);
	}

	@Override
	public List<ProductImage> findByProduct(Product product) {
		return productImageDAO.findByProduct(product);
	}

	@Override
	public void updateProductImage(ProductImage productImage) {
		productImageDAO.update(productImage);
	}

	@Override
	public void deletProductImage(ProductImage productImage) {
		productImageDAO.delete(productImage);
	}
	
}
