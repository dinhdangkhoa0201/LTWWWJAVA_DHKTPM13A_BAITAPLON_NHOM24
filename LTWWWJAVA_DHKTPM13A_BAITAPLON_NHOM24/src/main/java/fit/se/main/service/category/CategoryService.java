package fit.se.main.service.category;

import java.util.List;

import fit.se.main.model.Category;

public interface CategoryService {
	public Category createCategory(Category category);
	public void deleteCategory(Category category);
	public void updateCategory(Category category);
	public List<Category> findAll();
	public Category fingById(int id);
}
