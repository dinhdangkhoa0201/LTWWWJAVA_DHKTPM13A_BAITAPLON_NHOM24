package fit.se.main.dao.product;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.repository.ProductRepository;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product findById(int id) {
		return productRepository.getOne(id);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product create(Product entity) {
		return productRepository.save(entity);
	}

	@Override
	public Product update(Product entity) {
		return productRepository.save(entity);
	}

	@Override
	public void delete(Product entity) {
		productRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		productRepository.deleteById(entityId);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public Optional<Product> findByPrice(double price) {
		return null;
	}

}
