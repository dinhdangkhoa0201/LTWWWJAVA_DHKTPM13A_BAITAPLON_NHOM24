package fit.se.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.Category;
import fit.se.main.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	Optional<Product> findByCategory(Category category);
	Optional<Product> findByPrice(double price);
}
