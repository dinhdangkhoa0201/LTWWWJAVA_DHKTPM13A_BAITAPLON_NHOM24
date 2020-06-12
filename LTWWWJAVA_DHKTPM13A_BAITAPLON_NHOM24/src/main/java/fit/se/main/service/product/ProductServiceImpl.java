package fit.se.main.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.se.main.dao.product.ProductDAO;
import fit.se.main.model.Category;
import fit.se.main.model.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public Product createProduct(Product product) throws Exception {
		return productDAO.create(product);
	}

	@Override
	public Optional<Product> findByCategory(Category category) {
		return productDAO.findByCategory(category);
	}

	@Override
	public void deleteProduct(Product product) {
		productDAO.delete(product);
	}

	@Override
	public void deleteProductById(int productId) {
		productDAO.deleteById(productId);
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.update(product);
	}

	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}

}
