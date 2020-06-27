package fit.se.main.dao.product;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Supplier;
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
		return productRepository.saveAndFlush(entity);
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
	public List<Product> findByPrice(double pricemin, double pricemax) {
		return productRepository.findByPrice(pricemin, pricemax);
	}

	@Override
	public Integer quanityByCategory(int category_id) {
		return productRepository.quanityByCategory(category_id);
	}

	@Override
	public Integer quanityBySupplier(int supplier_id) {
		return productRepository.quanityBySupplier(supplier_id);
	}

	@Override
	public List<Product> findBySupplier(Supplier supplier) {
		return productRepository.findBySupplier(supplier);
	}

	@Override
	public Integer quanityByProduct(int product_id) {
		return productRepository.quanityByProduct(product_id);
	}

	@Override
	public List<Integer> findByNoSale() {
		return productRepository.findByNoSale();
	}

	@Override
	public List<Integer> findByLowSale() {
		return productRepository.findByLowSale();
	}

	@Override
	public List<Integer> findByMediumSale() {
		return productRepository.findByMediumSale();
	}

	@Override
	public List<Integer> findByHighSale() {
		return productRepository.findByHighSale();
	}

	@Override
	public List<Integer> findByTopSale() {
		return productRepository.findByTopSale();
	}

	@Override
	public List<Product> findByYear() {
		return productRepository.findByYear();
	}

	@Override
	public List<Product> findByMonth() {
		return productRepository.findByMonth();
	}

	@Override
	public List<Product> findByWeek() {
		return productRepository.findByWeek();
	}

}
