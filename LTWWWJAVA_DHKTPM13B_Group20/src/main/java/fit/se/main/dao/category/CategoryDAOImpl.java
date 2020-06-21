package fit.se.main.dao.category;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.model.Category;
import fit.se.main.repository.CategoryRepository;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category findById(int id) {
		return categoryRepository.getOne(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category create(Category entity) {
		return categoryRepository.save(entity);
	}

	@Override
	public Category update(Category entity) {
		return categoryRepository.save(entity);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		categoryRepository.deleteById(entityId);;
	}

	@Override
	public Optional<Category> findByName(String categoryName) {
		return null;
	}

}
