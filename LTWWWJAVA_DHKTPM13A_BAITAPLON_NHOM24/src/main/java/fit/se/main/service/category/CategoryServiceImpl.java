package fit.se.main.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.se.main.dao.category.CategoryDAO;
import fit.se.main.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public Category createCategory(Category category) {
		return categoryDAO.create(category);
	}

	@Override
	public void deleteCategory(Category category) {
		categoryDAO.delete(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDAO.update(category);
	}

	@Override
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public Category findById(int id) {
		return categoryDAO.findById(id);
	}

	@Override
	public void deleteById(int id) {
		categoryDAO.deleteById(id);
	}

}
