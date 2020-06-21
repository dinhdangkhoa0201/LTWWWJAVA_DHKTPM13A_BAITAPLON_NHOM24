package fit.se.main.service.category;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.model.Category;

public interface CategoryService {
	public Category createCategory(Category category);
	public void deleteCategory(Category category);
	public void updateCategory(Category category);
	public void deleteById(int id);
	public List<Category> findAll();
	public Category findById(int id);
}
