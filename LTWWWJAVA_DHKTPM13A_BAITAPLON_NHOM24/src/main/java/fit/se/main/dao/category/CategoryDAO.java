package fit.se.main.dao.category;

import java.util.Optional;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Category;

public interface CategoryDAO extends IOperations<Category>{
	Optional<Category> findByName(String categoryName);
}
