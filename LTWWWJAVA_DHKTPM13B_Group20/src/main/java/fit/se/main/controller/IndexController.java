package fit.se.main.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fit.se.main.dto.AccountCreateDTO;
import fit.se.main.model.Account;
import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Role;
import fit.se.main.model.SaleOrderDetail;
import fit.se.main.model.SaleOrderHeader;
import fit.se.main.model.Supplier;
import fit.se.main.model.UnitMeasure;
import fit.se.main.principal.AccountPricipal;
import fit.se.main.service.account.AccountService;
import fit.se.main.service.category.CategoryService;
import fit.se.main.service.product.ProductService;
import fit.se.main.service.sale_order_detail.SaleOrderDetailService;
import fit.se.main.service.sale_order_header.SaleOrderHeaderService;
import fit.se.main.service.supplier.SupplierService;
import fit.se.main.service.unit_measure.UnitMeasureService;

@Controller
public class IndexController {
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private ProductService productService;

	@Autowired
	private SaleOrderHeaderService saleOrderHeaderService;

	@Autowired
	private SaleOrderDetailService saleOrderDetailService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private UnitMeasureService unitMeasureService;

	@GetMapping("/")
	public String dashboard(Model model) {
		return "/admin/index";
	}

	@RequestMapping("/log-in")
	public String login(Model model, HttpServletRequest request) {
		System.out.println("Hello");
		
		System.out.println("Login account service : " + accountService);
		
		AccountCreateDTO createDTO = new AccountCreateDTO("Đinh Đăng Khoa", "0937602105", "dinhdangkhoa0201@gmail.com", "11111111", "11111111");
		try {
			if(!accountService.findByEmail(createDTO.getEmail()).isPresent()) {
				Account account = accountService.createAdmin(createDTO);
				System.out.println("Admin : " + account);
			}
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "sign-in";
	}


	@RequestMapping("/index")
	public String index(Model model, Principal principal) {
		AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
		System.out.println("account pricipal login : " + accountAdminPricipal);
		if(accountAdminPricipal != null) {
			Account account = accountService.findById(accountAdminPricipal.getAccountId());
			
			System.out.println("Account : " + account);
			System.out.println("Account Role : " + account.getRoles());
			
			model.addAttribute("account", account);
			for(Role role : account.getRoles()) {
				if(role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
					model.addAttribute("ROLE_ADMIN", role.getName());
				}
				break;
			}
		}
		return "index";
	}

	@GetMapping("/sign-out")
	public String signout(HttpServletRequest request, HttpServletResponse responses) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, responses, authentication);
		}
		return "redirect:/index";
	}

	@Transactional
	public void init() {		
		try {
			AccountCreateDTO accountMember = new AccountCreateDTO("ddk", "09376021052", "khoacyruss@gmail.com", "11111111", "11111111");
			Account account = null;
			if(!accountService.findByEmail(accountMember.getEmail()).isPresent()) {
				account = accountService.createMember(accountMember);
			} else {
				account = accountService.findById(2);
			}
			
			Category category = new Category("A");
			categoryService.createCategory(category);
			
			Supplier supplier = new Supplier("B");
			supplierService.createSupplier(supplier);

			UnitMeasure unitMeasure = null;
			if(unitMeasureService.findByName("C") == null) {
				unitMeasure = new UnitMeasure("C");
				unitMeasureService.createUnitMeasure(unitMeasure);
			} else {
				unitMeasure = unitMeasureService.findByName("C");
			}


			// List Product
			Product product1 = new Product("Gạo", 20000);
			product1.setCategory(category);
			product1.setSupplier(supplier);
			product1.setUnitMeasure(unitMeasure);
			Product product2 = new Product("Sua", 18000);
			product2.setCategory(category);
			product2.setSupplier(supplier);
			product2.setUnitMeasure(unitMeasure);
			
			productService.createProduct(product1);
			productService.createProduct(product2);

			SaleOrderHeader orderHeader = new SaleOrderHeader(account, LocalDateTime.now(), LocalDateTime.now(), "");
			saleOrderHeaderService.createOrder(orderHeader);

			SaleOrderDetail orderDetail1 = new SaleOrderDetail(orderHeader, product1, 1, product1.getPrice()); 
			SaleOrderDetail orderDetail2 = new SaleOrderDetail(orderHeader, product2, 2, product2.getPrice()); 
			saleOrderDetailService.createOrderDetail(orderDetail1);
			saleOrderDetailService.createOrderDetail(orderDetail2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
