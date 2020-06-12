package fit.se.main.dao.category.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.dao.category.CategoryDAO;
import fit.se.main.model.Category;
import fit.se.main.repository.CategoryRepository;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private CategoryRepository categoryReponsitory;
	
	@Override
	public Category findById(int id) {
		return categoryReponsitory.getOne(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryReponsitory.findAll();
	}

	@Override
	public Category create(Category entity) {
		return categoryReponsitory.save(entity);
	}

	@Override
	public Category update(Category entity) {
		return categoryReponsitory.saveAndFlush(entity);
	}

	@Override
	public void delete(Category entity) {
		categoryReponsitory.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		categoryReponsitory.deleteById(entityId);
	}

	@Override
	public Optional<Category> findByName(String categoryName) {
		return categoryReponsitory.findByCategoryName(categoryName);
	}

}
