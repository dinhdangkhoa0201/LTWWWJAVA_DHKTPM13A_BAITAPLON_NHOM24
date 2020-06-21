package fit.se.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.Product;
import fit.se.main.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>{
	List<ProductImage> findByProduct(Product product);
}
