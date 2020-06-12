package fit.se.main.dao.product_image.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.dao.product_image.ProductImageDAO;
import fit.se.main.model.Product;
import fit.se.main.model.ProductImage;
import fit.se.main.repository.ProductImageRepository;

@Repository
public class ProductImageDAOImpl implements ProductImageDAO{
	
	@Autowired
	private ProductImageRepository productImageRepository;
	@Override
	public ProductImage findById(int id) {
		return productImageRepository.getOne(id);
	}

	@Override
	public List<ProductImage> findAll() {
		return productImageRepository.findAll();
	}

	@Override
	public ProductImage create(ProductImage entity) {
		return productImageRepository.save(entity);
	}

	@Override
	public ProductImage update(ProductImage entity) {
		return productImageRepository.save(entity);
	}

	@Override
	public void delete(ProductImage entity) {
		productImageRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		productImageRepository.deleteById(entityId);
	}

	@Override
	public List<ProductImage> findByProduct(Product product) {
		return productImageRepository.findByProduct(product);
	}

}
