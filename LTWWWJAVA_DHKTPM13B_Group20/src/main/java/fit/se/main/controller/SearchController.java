package fit.se.main.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Supplier;
import fit.se.main.service.category.CategoryService;
import fit.se.main.service.product.ProductService;
import fit.se.main.service.supplier.SupplierService;

@Controller
public class SearchController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SupplierService supplierService;
	@GetMapping("/listProduct")
	public String loadDanhSachSanPham(Model model) {
		List<Product> productsALL = productService.findAll();
		List<Product> products = productService.findAll();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
		List<Integer> quanityByCategory = new ArrayList<Integer>();
		List<Integer> quanityBySupplier = new ArrayList<Integer>();
		List<Integer> quanityByProduct = new ArrayList<Integer>();
		Map<Product, Integer> mapProduct = new HashMap<Product, Integer>();
		Map<Category, Integer> mapCategory = new HashMap<Category, Integer>();
		Map<Supplier, Integer> mapSupplier = new HashMap<Supplier, Integer>();
		List<Integer> findProductByNoSale = productService.findByNoSale();
		List<Integer> findProductByLowSale = productService.findByLowSale();
		List<Integer> findProductByMediumSale = productService.findByMediumSale();
		List<Integer> findProductByHighSale = productService.findByHighSale();
		List<Integer> findProductByTopSale = productService.findByTopSale();
		List<Product> findByYear = productService.findByYear();
		List<Product> findByMonth = productService.findByMonth();
		List<Product> findByWeek = productService.findByWeek();
		for (Category category : categories) {
			quanityByCategory.add(productService.quanityByCategory(category.getCategoryId()));
		}
		
		int i = 0;
		for (Category category : categories) {
			mapCategory.put(category, quanityByCategory.get(i));
			i++;
		}
		
		for (Supplier supplier : suppliers) {
			quanityBySupplier.add(productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		int j = 0;
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, quanityBySupplier.get(j));
			j++;
		}
		
		for (Product product : products) {
			quanityByProduct.add(productService.quanityByProduct(product.getProductId()));
		}
		int k = 0;
		for (Product product : products) {
			mapProduct.put(product, quanityByProduct.get(k));
			k++;
		}
		System.out.println(mapCategory);
		model.addAttribute("mapProduct", mapProduct);
		model.addAttribute("mapCategory", mapCategory);
		model.addAttribute("mapSupplier", mapSupplier);
		model.addAttribute("productsAll", productsALL);
		model.addAttribute("categories", categories);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("findProductByNoSale", findProductByNoSale);
		model.addAttribute("findProductByLowSale", findProductByLowSale);
		model.addAttribute("findProductByMediumSale", findProductByMediumSale);
		model.addAttribute("findProductByHighSale", findProductByHighSale);
		model.addAttribute("findProductByTopSale", findProductByTopSale);
		model.addAttribute("findByYear", findByYear);
		model.addAttribute("findByMonth", findByMonth);
		model.addAttribute("findByWeek", findByWeek);
		return "listProduct";
	}
	
	public static String getCurrentUrl(HttpServletRequest request) throws URISyntaxException, MalformedURLException{
	    URL url = new URL(request.getRequestURL().toString());
	    String host  = url.getHost();
	    String userInfo = url.getUserInfo();
	    String scheme = url.getProtocol();
	    int port = url.getPort();
	    String path = (String) request.getAttribute("javax.servlet.forward.request_uri");
	    String query = (String) request.getAttribute("javax.servlet.forward.query_string");

	    URI uri = new URI(scheme,userInfo,host,port,path,query,null);
	    return uri.toString();
	}
	
	@GetMapping("/category")
	public String loadDanhSachSanPhamByCategory(Model model, @RequestParam(name = "category_id")int category_id) {
		Category category = categoryService.findById(category_id);
		List<Product> products = productService.findByCategory(category);
		List<Product> productsAll = productService.findAll();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
		List<Integer> quanityByProduct = new ArrayList<Integer>();
		Map<Product, Integer> mapProduct = new HashMap<Product, Integer>();
		List<Integer> quanityByCategory = new ArrayList<Integer>();
		List<Integer> quanityBySupplier = new ArrayList<Integer>();
		Map<Category, Integer> mapCategory = new HashMap<Category, Integer>();
		Map<Supplier, Integer> mapSupplier = new HashMap<Supplier, Integer>();
		List<Integer> findProductByNoSale = productService.findByNoSale();
		List<Integer> findProductByLowSale = productService.findByLowSale();
		List<Integer> findProductByMediumSale = productService.findByMediumSale();
		List<Integer> findProductByHighSale = productService.findByHighSale();
		List<Integer> findProductByTopSale = productService.findByTopSale();
		List<Product> findByYear = productService.findByYear();
		List<Product> findByMonth = productService.findByMonth();
		List<Product> findByWeek = productService.findByWeek();
		for (Category category1 : categories) {
			quanityByCategory.add(productService.quanityByCategory(category1.getCategoryId()));
		}
		
		int i = 0;
		for (Category category2 : categories) {
			mapCategory.put(category2, quanityByCategory.get(i));
			i++;
		}
		
		for (Supplier supplier : suppliers) {
			quanityBySupplier.add(productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		int j = 0;
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, quanityBySupplier.get(j));
			j++;
		}
		for (Product product : products) {
			quanityByProduct.add(productService.quanityByProduct(product.getProductId()));
		}
		int k = 0;
		for (Product product : products) {
			mapProduct.put(product, quanityByProduct.get(k));
			k++;
		}
		model.addAttribute("mapProduct", mapProduct);
		model.addAttribute("mapCategory", mapCategory);
		model.addAttribute("mapSupplier", mapSupplier);
		model.addAttribute("productsAll", productsAll);
		model.addAttribute("categories", categories);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("findProductByNoSale", findProductByNoSale);
		model.addAttribute("findProductByLowSale", findProductByLowSale);
		model.addAttribute("findProductByMediumSale", findProductByMediumSale);
		model.addAttribute("findProductByHighSale", findProductByHighSale);
		model.addAttribute("findProductByTopSale", findProductByTopSale);
		model.addAttribute("findByYear", findByYear);
		model.addAttribute("findByMonth", findByMonth);
		model.addAttribute("findByWeek", findByWeek);
		return "listProduct";
	}
	@GetMapping("/supplier")
	public String loadDanhSachSanPhamBySupplier(Model model, @RequestParam(name = "supplier_id")int supplier_id) {
		Supplier supplier = supplierService.findById(supplier_id);
		List<Product> products = productService.findBySupplier(supplier);
		List<Product> productsAll = productService.findAll();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
		List<Integer> quanityByCategory = new ArrayList<Integer>();
		List<Integer> quanityBySupplier = new ArrayList<Integer>();
		List<Integer> quanityByProduct = new ArrayList<Integer>();
		Map<Product, Integer> mapProduct = new HashMap<Product, Integer>();
		Map<Category, Integer> mapCategory = new HashMap<Category, Integer>();
		Map<Supplier, Integer> mapSupplier = new HashMap<Supplier, Integer>();
		List<Integer> findProductByNoSale = productService.findByNoSale();
		List<Integer> findProductByLowSale = productService.findByLowSale();
		List<Integer> findProductByMediumSale = productService.findByMediumSale();
		List<Integer> findProductByHighSale = productService.findByHighSale();
		List<Integer> findProductByTopSale = productService.findByTopSale();
		List<Product> findByYear = productService.findByYear();
		List<Product> findByMonth = productService.findByMonth();
		List<Product> findByWeek = productService.findByWeek();
		model.addAttribute("findByYear", findByYear);
		model.addAttribute("findByMonth", findByMonth);
		model.addAttribute("findByWeek", findByWeek);
		for (Category category : categories) {
			quanityByCategory.add(productService.quanityByCategory(category.getCategoryId()));
		}
		
		int i = 0;
		for (Category category : categories) {
			mapCategory.put(category, quanityByCategory.get(i));
			i++;
		}
		
		for (Supplier supplier1 : suppliers) {
			quanityBySupplier.add(productService.quanityBySupplier(supplier1.getSupplierId()));
		}
		
		int j = 0;
		for (Supplier supplier2 : suppliers) {
			mapSupplier.put(supplier2, quanityBySupplier.get(j));
			j++;
		}
		for (Product product : products) {
			quanityByProduct.add(productService.quanityByProduct(product.getProductId()));
		}
		int k = 0;
		for (Product product : products) {
			mapProduct.put(product, quanityByProduct.get(k));
			k++;
		}
		model.addAttribute("mapProduct", mapProduct);
		model.addAttribute("mapCategory", mapCategory);
		model.addAttribute("mapSupplier", mapSupplier);
		model.addAttribute("productsAll", productsAll);
		model.addAttribute("categories", categories);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("findProductByNoSale", findProductByNoSale);
		model.addAttribute("findProductByLowSale", findProductByLowSale);
		model.addAttribute("findProductByMediumSale", findProductByMediumSale);
		model.addAttribute("findProductByHighSale", findProductByHighSale);
		model.addAttribute("findProductByTopSale", findProductByTopSale);
		model.addAttribute("findByYear", findByYear);
		model.addAttribute("findByMonth", findByMonth);
		model.addAttribute("findByWeek", findByWeek);
		return "listProduct";
	}
	
	@GetMapping("/price")
	public String loadDanhSachSanPhamByPrice(Model model, @RequestParam(name = "pricemin")String pricemin, @RequestParam(name = "pricemax")String pricemax) {
		List<Product> products = productService.findByPrice(Double.parseDouble(pricemin), Double.parseDouble(pricemax));
		List<Product> productsAll = productService.findAll();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
		List<Integer> quanityByCategory = new ArrayList<Integer>();
		List<Integer> quanityBySupplier = new ArrayList<Integer>();
		List<Integer> quanityByProduct = new ArrayList<Integer>();
		Map<Product, Integer> mapProduct = new HashMap<Product, Integer>();
		Map<Category, Integer> mapCategory = new HashMap<Category, Integer>();
		Map<Supplier, Integer> mapSupplier = new HashMap<Supplier, Integer>();
		List<Integer> findProductByNoSale = productService.findByNoSale();
		List<Integer> findProductByLowSale = productService.findByLowSale();
		List<Integer> findProductByMediumSale = productService.findByMediumSale();
		List<Integer> findProductByHighSale = productService.findByHighSale();
		List<Integer> findProductByTopSale = productService.findByTopSale();
		List<Product> findByYear = productService.findByYear();
		List<Product> findByMonth = productService.findByMonth();
		List<Product> findByWeek = productService.findByWeek();
		for (Category category : categories) {
			quanityByCategory.add(productService.quanityByCategory(category.getCategoryId()));
		}
		
		int i = 0;
		for (Category category : categories) {
			mapCategory.put(category, quanityByCategory.get(i));
			i++;
		}
		
		for (Supplier supplier1 : suppliers) {
			quanityBySupplier.add(productService.quanityBySupplier(supplier1.getSupplierId()));
		}
		
		int j = 0;
		for (Supplier supplier2 : suppliers) {
			mapSupplier.put(supplier2, quanityBySupplier.get(j));
			j++;
		}
		
		for (Product product : products) {
			quanityByProduct.add(productService.quanityByProduct(product.getProductId()));
		}
		int k = 0;
		for (Product product : products) {
			mapProduct.put(product, quanityByProduct.get(k));
			k++;
		}
		model.addAttribute("mapProduct", mapProduct);
		model.addAttribute("mapCategory", mapCategory);
		model.addAttribute("mapSupplier", mapSupplier);
		model.addAttribute("productsAll", productsAll);
		model.addAttribute("categories", categories);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("findProductByNoSale", findProductByNoSale);
		model.addAttribute("findProductByLowSale", findProductByLowSale);
		model.addAttribute("findProductByMediumSale", findProductByMediumSale);
		model.addAttribute("findProductByHighSale", findProductByHighSale);
		model.addAttribute("findProductByTopSale", findProductByTopSale);
		model.addAttribute("findByYear", findByYear);
		model.addAttribute("findByMonth", findByMonth);
		model.addAttribute("findByWeek", findByWeek);
		return "listProduct";
	}
	@GetMapping("/sale")
	public String loadDanhSachSanPhamBySale(Model model, @RequestParam(name = "type")String type) {
		List<Product> productsALL = productService.findAll();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
		List<Integer> quanityByCategory = new ArrayList<Integer>();
		List<Integer> quanityBySupplier = new ArrayList<Integer>();
		List<Integer> quanityByProduct = new ArrayList<Integer>();
		Map<Product, Integer> mapProduct = new HashMap<Product, Integer>();
		Map<Category, Integer> mapCategory = new HashMap<Category, Integer>();
		Map<Supplier, Integer> mapSupplier = new HashMap<Supplier, Integer>();
		List<Integer> findProductByNoSale = productService.findByNoSale();
		List<Integer> findProductByLowSale = productService.findByLowSale();
		List<Integer> findProductByMediumSale = productService.findByMediumSale();
		List<Integer> findProductByHighSale = productService.findByHighSale();
		List<Integer> findProductByTopSale = productService.findByTopSale();
		List<Product> products = new ArrayList<Product>();
		List<Product> findByYear = productService.findByYear();
		List<Product> findByMonth = productService.findByMonth();
		List<Product> findByWeek = productService.findByWeek();
		for (Category category : categories) {
			quanityByCategory.add(productService.quanityByCategory(category.getCategoryId()));
		}
		
		int i = 0;
		for (Category category : categories) {
			mapCategory.put(category, quanityByCategory.get(i));
			i++;
		}
		
		for (Supplier supplier : suppliers) {
			quanityBySupplier.add(productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		int j = 0;
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, quanityBySupplier.get(j));
			j++;
		}
		switch (type) {
		case "NoSale":
			for (int product_id : findProductByNoSale) {
				products.add(productService.findById(product_id));
			}
			break;
		case "LowSale":
			for (int product_id : findProductByLowSale) {
				products.add(productService.findById(product_id));
			}
			break;
		case "MediumSale":
			for (int product_id : findProductByMediumSale) {
				products.add(productService.findById(product_id));
			}
			break;
		case "HighSale":
			for (int product_id : findProductByHighSale) {
				products.add(productService.findById(product_id));
			}
			break;
		case "TopSale":
			for (int product_id : findProductByTopSale) {
				products.add(productService.findById(product_id));
			}
			break;
		default:
			break;
		}
		for (Product product : products) {
			quanityByProduct.add(productService.quanityByProduct(product.getProductId()));
		}
		int k = 0;
		for (Product product : products) {
			mapProduct.put(product, quanityByProduct.get(k));
			k++;
		}
		model.addAttribute("mapProduct", mapProduct);
		model.addAttribute("mapCategory", mapCategory);
		model.addAttribute("mapSupplier", mapSupplier);
		model.addAttribute("productsAll", productsALL);
		model.addAttribute("categories", categories);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("findProductByNoSale", findProductByNoSale);
		model.addAttribute("findProductByLowSale", findProductByLowSale);
		model.addAttribute("findProductByMediumSale", findProductByMediumSale);
		model.addAttribute("findProductByHighSale", findProductByHighSale);
		model.addAttribute("findProductByTopSale", findProductByTopSale);
		model.addAttribute("findByYear", findByYear);
		model.addAttribute("findByMonth", findByMonth);
		model.addAttribute("findByWeek", findByWeek);
		return "listProduct";
	}
	@GetMapping("/date")
	public String loadDanhSachSanPhamByDate(Model model, @RequestParam(name = "type")String type) {
		List<Product> productsALL = productService.findAll();
		List<Product> products = new ArrayList<Product>();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
		List<Integer> quanityByCategory = new ArrayList<Integer>();
		List<Integer> quanityBySupplier = new ArrayList<Integer>();
		List<Integer> quanityByProduct = new ArrayList<Integer>();
		Map<Product, Integer> mapProduct = new HashMap<Product, Integer>();
		Map<Category, Integer> mapCategory = new HashMap<Category, Integer>();
		Map<Supplier, Integer> mapSupplier = new HashMap<Supplier, Integer>();
		List<Integer> findProductByNoSale = productService.findByNoSale();
		List<Integer> findProductByLowSale = productService.findByLowSale();
		List<Integer> findProductByMediumSale = productService.findByMediumSale();
		List<Integer> findProductByHighSale = productService.findByHighSale();
		List<Integer> findProductByTopSale = productService.findByTopSale();
		List<Product> findByYear = productService.findByYear();
		List<Product> findByMonth = productService.findByMonth();
		List<Product> findByWeek = productService.findByWeek();
		for (Category category : categories) {
			quanityByCategory.add(productService.quanityByCategory(category.getCategoryId()));
		}
		
		int i = 0;
		for (Category category : categories) {
			mapCategory.put(category, quanityByCategory.get(i));
			i++;
		}
		
		for (Supplier supplier : suppliers) {
			quanityBySupplier.add(productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		int j = 0;
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, quanityBySupplier.get(j));
			j++;
		}
		
		switch (type) {
		case "year":
			products = findByYear;
			break;
		case "month":
			products = findByMonth;
			break;
		case "week":
			products = findByWeek;
			break;
		default:
			break;
		}
		for (Product product : products) {
			quanityByProduct.add(productService.quanityByProduct(product.getProductId()));
		}
		int k = 0;
		for (Product product : products) {
			mapProduct.put(product, quanityByProduct.get(k));
			k++;
		}
		model.addAttribute("mapProduct", mapProduct);
		model.addAttribute("mapCategory", mapCategory);
		model.addAttribute("mapSupplier", mapSupplier);
		model.addAttribute("productsAll", productsALL);
		model.addAttribute("categories", categories);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("findProductByNoSale", findProductByNoSale);
		model.addAttribute("findProductByLowSale", findProductByLowSale);
		model.addAttribute("findProductByMediumSale", findProductByMediumSale);
		model.addAttribute("findProductByHighSale", findProductByHighSale);
		model.addAttribute("findProductByTopSale", findProductByTopSale);
		model.addAttribute("findByYear", findByYear);
		model.addAttribute("findByMonth", findByMonth);
		model.addAttribute("findByWeek", findByWeek);
		return "listProduct";
	}
}
