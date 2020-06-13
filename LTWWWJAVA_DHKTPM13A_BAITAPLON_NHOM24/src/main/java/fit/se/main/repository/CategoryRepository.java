package fit.se.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Optional<Category> findByCategoryName(String categoryName);
	
}