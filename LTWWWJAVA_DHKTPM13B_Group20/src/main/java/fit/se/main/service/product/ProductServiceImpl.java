package fit.se.main.service.product;

import java.awt.print.Book;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		product.setQuantity(productCreateDTO.getQuantity());
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

	@Override
	public Integer quanityByCategory(int category_id) {
		return productDAO.quanityByCategory(category_id);
	}

	@Override
	public Integer quanityBySupplier(int supplier_id) {
		return productDAO.quanityBySupplier(supplier_id);
	}

	@Override
	public List<Product> findBySupplier(Supplier supplier) {
		return productDAO.findBySupplier(supplier);
	}

	@Override
	public List<Product> findByPrice(double pricemin, double pricemax) {
		return productDAO.findByPrice(pricemin, pricemax);
	}

	@Override
	public Integer quanityByProduct(int product_id) {
		return productDAO.quanityByProduct(product_id);
	}

	@Override
	public List<Integer> findByNoSale() {
		return productDAO.findByNoSale();
	}

	@Override
	public List<Integer> findByLowSale() {
		return productDAO.findByLowSale();
	}

	@Override
	public List<Integer> findByMediumSale() {
		return productDAO.findByMediumSale();
	}

	@Override
	public List<Integer> findByHighSale() {
		return productDAO.findByHighSale();
	}

	@Override
	public List<Integer> findByTopSale() {
		return productDAO.findByTopSale();
	}

	@Override
	public List<Product> findByYear() {
		return productDAO.findByYear();
	}

	@Override
	public List<Product> findByMonth() {
		return productDAO.findByMonth();
	}

	@Override
	public List<Product> findByWeek() {
		return productDAO.findByWeek();
	}
<<<<<<< HEAD
=======
	@Override
	public Page<Product> findPaginated(Pageable pageable, List<Product> products) {
		int pagesize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage*pagesize;
		List<Product> list;
		if(products.size() < startItem) {
			list = Collections.emptyList();
		}else {
			int toIndex = Math.min(startItem + pagesize, products.size());
			list = products.subList(startItem, toIndex);
		}
		Page<Product> page = new PageImpl<Product>(list, PageRequest.of(currentPage, pagesize), products.size());
		return page;
	}
>>>>>>> vophan
	
}
