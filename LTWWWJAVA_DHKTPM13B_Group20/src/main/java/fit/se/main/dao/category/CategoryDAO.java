package fit.se.main.dao.category;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Category;

public interface CategoryDAO extends IOperations<Category>{
	Optional<Category> findByName(String categoryName);
}
