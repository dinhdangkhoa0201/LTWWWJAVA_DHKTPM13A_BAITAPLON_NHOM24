package fit.se.main.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fit.se.main.model.Account;
import fit.se.main.model.CartInfo;
import fit.se.main.model.CartLineInfo;
import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Role;
import fit.se.main.model.Supplier;
import fit.se.main.principal.AccountPricipal;
import fit.se.main.service.account.AccountService;
import fit.se.main.service.category.CategoryService;
import fit.se.main.service.product.ProductService;
import fit.se.main.service.supplier.SupplierService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/index/AddModel")
	public String addToCartModel(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, Principal principal, HttpServletRequest request , Model model, @RequestParam(name = "product_id") int product_id, @RequestParam(name = "quatityby") int quatityby) {
		List<Product> products = productService.findAll();
		int currentPage = page.orElse(1);
	    int pageSize = size.orElse(21);
	    Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage-1, pageSize), products);
	    model.addAttribute("productPage", productPage);
	    int totalPages = productPage.getTotalPages();
	    if(totalPages > 0) {
	    	List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
	    	model.addAttribute("pageNumbers", pageNumbers);
	    }
		List<Product> productsALL = productService.findAll();		
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
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
			mapCategory.put(category, productService.quanityByCategory(category.getCategoryId()));
		}
		
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		for (Product product : productPage.getContent()) {
			mapProduct.put(product, productService.quanityByProduct(product.getProductId()));
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
		HttpSession session  = request.getSession();
		CartInfo cartInfo = null  ;
		Object objCartInfo = session.getAttribute("myCart");
		Product product = productService.findById(product_id);
		Supplier supplier = product.getSupplier();
		if(objCartInfo !=null) {
			cartInfo = (CartInfo) objCartInfo;
		}else {
			cartInfo = new CartInfo();
			session.setAttribute("myCart",cartInfo);
		}
		CartLineInfo cartLineInfo;
		cartLineInfo = new CartLineInfo(product.getProductId(), product.getProductName(), product.getPrice(), quatityby, quatityby*product.getPrice(),product.getSupplier(), product.getCategory() ,product.getImage());
		cartInfo.addProduct(cartLineInfo);
		model.addAttribute("myCart",cartInfo);
		model.addAttribute("product",product);
		model.addAttribute("supplier",supplier);
		if(principal!=null) {
	    	AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
			if(accountAdminPricipal != null) {
				Account account = accountService.findById(accountAdminPricipal.getAccountId());
				model.addAttribute("account", account);
				for(Role role : account.getRoles()) {
					if(role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
						model.addAttribute("ROLE_ADMIN", role.getName());
					}
					break;
				}
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		return "redirect:/index";

	}
	
	@GetMapping("/index")
	public String loadDanhSachSanPham(HttpServletRequest request,Principal principal, Model model,@RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		List<Product> products = productService.findAll();
		int currentPage = page.orElse(1);
	    int pageSize = size.orElse(21);
	    Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage-1, pageSize), products);
	    model.addAttribute("productPage", productPage);
	    int totalPages = productPage.getTotalPages();
	    if(totalPages > 0) {
	    	List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
	    	model.addAttribute("pageNumbers", pageNumbers);
	    }
	    
	    HttpSession session  = request.getSession();
		CartInfo cartInfo = null;
		Object objCartInfo = session.getAttribute("myCart");
		if(objCartInfo !=null) {
			cartInfo = (CartInfo) objCartInfo;
		}else {
			cartInfo = new CartInfo();
			session.setAttribute("myCart",cartInfo);
		}
		model.addAttribute("myCart",cartInfo);
	    if(principal!=null) {
	    	AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
			if(accountAdminPricipal != null) {
				Account account = accountService.findById(accountAdminPricipal.getAccountId());
				model.addAttribute("account", account);
				for(Role role : account.getRoles()) {
					if(role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
						model.addAttribute("ROLE_ADMIN", role.getName());
					}
					break;
				}
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		
		
		List<Product> productsALL = productService.findAll();		
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
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
			mapCategory.put(category, productService.quanityByCategory(category.getCategoryId()));
		}
		
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		for (Product product : productPage.getContent()) {
			mapProduct.put(product, productService.quanityByProduct(product.getProductId()));
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
		return "index";
	}
	
	@GetMapping("/category")
	public String loadDanhSachSanPhamByCategory(HttpServletRequest request,Principal principal,Model model, @RequestParam(name = "category_id")int category_id) {
		List<Product> products = productService.findByCategory(categoryService.findById(category_id));
		List<Product> productsAll = productService.findAll();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
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
		
		HttpSession session  = request.getSession();
		CartInfo cartInfo = null;
		Object objCartInfo = session.getAttribute("myCart");
		if(objCartInfo !=null) {
			cartInfo = (CartInfo) objCartInfo;
		}else {
			cartInfo = new CartInfo();
			session.setAttribute("myCart",cartInfo);
		}
		model.addAttribute("myCart",cartInfo);
	    
		if(principal!=null) {
	    	AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
			if(accountAdminPricipal != null) {
				Account account = accountService.findById(accountAdminPricipal.getAccountId());
				model.addAttribute("account", account);
				for(Role role : account.getRoles()) {
					if(role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
						model.addAttribute("ROLE_ADMIN", role.getName());
					}
					break;
				}
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		
		for (Category category : categories) {
			mapCategory.put(category, productService.quanityByCategory(category.getCategoryId()));
		}
		
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		for (Product product : products) {
			mapProduct.put(product, productService.quanityByProduct(product.getProductId()));
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
		return "index";
	}
	@GetMapping("/supplier")
	public String loadDanhSachSanPhamBySupplier(HttpServletRequest request,Principal principal, Model model, @RequestParam(name = "supplier_id")int supplier_id) {
		List<Product> products = productService.findBySupplier(supplierService.findById(supplier_id));
		List<Product> productsAll = productService.findAll();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
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
		
		HttpSession session  = request.getSession();
		CartInfo cartInfo = null;
		Object objCartInfo = session.getAttribute("myCart");
		if(objCartInfo !=null) {
			cartInfo = (CartInfo) objCartInfo;
		}else {
			cartInfo = new CartInfo();
			session.setAttribute("myCart",cartInfo);
		}
		model.addAttribute("myCart",cartInfo);
	    
		if(principal!=null) {
	    	AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
			if(accountAdminPricipal != null) {
				Account account = accountService.findById(accountAdminPricipal.getAccountId());
				model.addAttribute("account", account);
				for(Role role : account.getRoles()) {
					if(role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
						model.addAttribute("ROLE_ADMIN", role.getName());
					}
					break;
				}
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		
		for (Category category : categories) {
			mapCategory.put(category, productService.quanityByCategory(category.getCategoryId()));
		}
		
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		for (Product product : products) {
			mapProduct.put(product, productService.quanityByProduct(product.getProductId()));
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
		return "index";
	}
	
	@GetMapping("/price")
	public String loadDanhSachSanPhamByPrice(HttpServletRequest request,Principal principal, Model model, @RequestParam(name = "pricemin")String pricemin, @RequestParam(name = "pricemax")String pricemax) {
		List<Product> products = productService.findByPrice(Double.parseDouble(pricemin), Double.parseDouble(pricemax));
		List<Product> productsAll = productService.findAll();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
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
		
		HttpSession session  = request.getSession();
		CartInfo cartInfo = null;
		Object objCartInfo = session.getAttribute("myCart");
		if(objCartInfo !=null) {
			cartInfo = (CartInfo) objCartInfo;
		}else {
			cartInfo = new CartInfo();
			session.setAttribute("myCart",cartInfo);
		}
		model.addAttribute("myCart",cartInfo);
	    
		if(principal!=null) {
	    	AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
			if(accountAdminPricipal != null) {
				Account account = accountService.findById(accountAdminPricipal.getAccountId());
				model.addAttribute("account", account);
				for(Role role : account.getRoles()) {
					if(role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
						model.addAttribute("ROLE_ADMIN", role.getName());
					}
					break;
				}
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		
		for (Category category : categories) {
			mapCategory.put(category, productService.quanityByCategory(category.getCategoryId()));
		}
		
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		for (Product product : products) {
			mapProduct.put(product, productService.quanityByProduct(product.getProductId()));
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
		return "index";
	}
	
	@GetMapping("/sale")
	public String loadDanhSachSanPhamBySale(HttpServletRequest request,Principal principal, Model model, @RequestParam(name = "type")String type) {
		List<Product> productsALL = productService.findAll();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
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
		List<Product> products = new ArrayList<Product>();
		
		HttpSession session  = request.getSession();
		CartInfo cartInfo = null;
		Object objCartInfo = session.getAttribute("myCart");
		if(objCartInfo !=null) {
			cartInfo = (CartInfo) objCartInfo;
		}else {
			cartInfo = new CartInfo();
			session.setAttribute("myCart",cartInfo);
		}
		model.addAttribute("myCart",cartInfo);
	    
		if(principal!=null) {
	    	AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
			if(accountAdminPricipal != null) {
				Account account = accountService.findById(accountAdminPricipal.getAccountId());
				model.addAttribute("account", account);
				for(Role role : account.getRoles()) {
					if(role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
						model.addAttribute("ROLE_ADMIN", role.getName());
					}
					break;
				}
			}
	    }
		else {
			model.addAttribute("account", null);
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
		for (Category category : categories) {
			mapCategory.put(category, productService.quanityByCategory(category.getCategoryId()));
		}
		
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		for (Product product : products) {
			mapProduct.put(product, productService.quanityByProduct(product.getProductId()));
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
		return "index";
	}
	@GetMapping("/date")
	public String loadDanhSachSanPhamByDate(HttpServletRequest request,Principal principal,Model model, @RequestParam(name = "type")String type) {
		List<Product> productsALL = productService.findAll();
		List<Product> products = new ArrayList<Product>();
		List<Category> categories = categoryService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
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
		HttpSession session  = request.getSession();
		CartInfo cartInfo = null;
		Object objCartInfo = session.getAttribute("myCart");
		if(objCartInfo !=null) {
			cartInfo = (CartInfo) objCartInfo;
		}else {
			cartInfo = new CartInfo();
			session.setAttribute("myCart",cartInfo);
		}
		model.addAttribute("myCart",cartInfo);
	    
		if(principal!=null) {
	    	AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
			if(accountAdminPricipal != null) {
				Account account = accountService.findById(accountAdminPricipal.getAccountId());
				model.addAttribute("account", account);
				for(Role role : account.getRoles()) {
					if(role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
						model.addAttribute("ROLE_ADMIN", role.getName());
					}
					break;
				}
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		switch (type) {
		case "any":
			products = productsALL;
			break;
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
		
		for (Category category : categories) {
			mapCategory.put(category, productService.quanityByCategory(category.getCategoryId()));
		}
		
		for (Supplier supplier : suppliers) {
			mapSupplier.put(supplier, productService.quanityBySupplier(supplier.getSupplierId()));
		}
		
		for (Product product : products) {
			mapProduct.put(product, productService.quanityByProduct(product.getProductId()));
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
		return "index";
	}
}
