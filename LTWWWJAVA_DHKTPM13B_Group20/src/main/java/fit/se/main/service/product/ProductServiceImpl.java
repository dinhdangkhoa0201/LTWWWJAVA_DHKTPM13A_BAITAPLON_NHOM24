package fit.se.main.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.dao.category.CategoryDAO;
import fit.se.main.dao.product.ProductDAO;
import fit.se.main.dao.supplier.SupplierDAO;
import fit.se.main.dao.unit_measure.UnitMeasureDAO;
import fit.se.main.dto.ProductCreateDTO;
import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Supplier;
import fit.se.main.model.UnitMeasure;

@Service
public class ProductServiceImpl implements ProductService{
	
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private UnitMeasureDAO unitMeasureDAO;
	
	@Autowired
	private SupplierDAO supplierDAO;
	
	@Override
	public Product createProduct(Product product) throws Exception {
		return productDAO.create(product);
	}
	
	@Override
	public Product createProduct(ProductCreateDTO productCreateDTO) throws Exception {
		System.out.println("2. Product Create DTO " + productCreateDTO);
		Product product = new Product();
		
		product.setProductName(productCreateDTO.getProductName());
		product.setPrice(productCreateDTO.getPrice());
		product.setSellingPrice(productCreateDTO.getSellingPrice());
		product.setQuantity(Integer.parseInt(productCreateDTO.getQuantity()));
		product.setNote(productCreateDTO.getNote());
		
		product.setImage(null);
		
		Category category = categoryDAO.findById(productCreateDTO.getCategory());
		UnitMeasure unitMeasure = unitMeasureDAO.findById(productCreateDTO.getUnitmeasure());
		Supplier supplier = supplierDAO.findById(productCreateDTO.getSupplier());
		
		product.setCategory(category);
		product.setUnitMeasure(unitMeasure);
		product.setSupplier(supplier);
		
		return productDAO.create(product);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productDAO.findByCategory(category);
	}

	@Override
	public void deleteProduct(Product product) {
		productDAO.delete(product);
	}

	@Override
	public void deleteProductById(int productId) {
		productDAO.deleteById(productId);
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.update(product);
	}

	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	public Product findById(int productId) {
		return productDAO.findById(productId);
	}

}
