package fit.se.main.dao.product_image;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.Product;
import fit.se.main.model.ProductImage;

@Repository
public class ProductImageDAOImpl implements ProductImageDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ProductImage findById(int id) {
		return em.find(ProductImage.class, id);
	}

	@Override
	public List<ProductImage> findAll() {
		return em.createQuery("findAllProductImage", ProductImage.class).getResultList();
	}

	@Override
	public ProductImage create(ProductImage entity) {
		em.persist(entity);
		em.flush();
		return this.findById(entity.getId());
	}

	@Override
	public ProductImage update(ProductImage entity) {
		em.merge(entity);
		em.flush();
		return entity;
	}

	@Override
	public void delete(ProductImage entity) {
		em.remove(em.find(ProductImage.class, entity.getId()));
	}

	@Override
	public void deleteById(int entityId) {
		em.remove(em.find(ProductImage.class, entityId));
	}

	@Override
	public List<ProductImage> findByProduct(Product product) {
		return em.createNamedQuery("findProductImageByProduct", ProductImage.class).setParameter("product", product).getResultList();
	}

}
