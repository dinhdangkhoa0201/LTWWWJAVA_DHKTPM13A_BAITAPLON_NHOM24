package fit.se.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Supplier;
import fit.se.main.service.category.CategoryService;
import fit.se.main.service.product.ProductService;
import fit.se.main.service.supplier.SupplierService;
@Controller
public class PaymentInfoController {

	@Autowired
	private ProductService productService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/Thongtindathang")
	public String loadDetailProduct(Model model,@RequestParam (name = "product_id") int product_id) {
		Product product = productService.findById(product_id);
		Category category = product.getCategory();
		Supplier supplier = product.getSupplier();
		model.addAttribute("product", product);
		model.addAttribute("category", category);
		model.addAttribute("supplier", supplier);
		return "Thongtindathang";
	}
}
