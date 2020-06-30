package fit.se.main.controller;

import java.security.Principal;
import java.time.LocalDate;

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
	
	@RequestMapping("/sign-in")
	public String singin(Model model) {
		return "sing-in";
	}

	@RequestMapping("/")
	public String login(Model model, HttpServletRequest request) {
		System.out.println("Hello");
		
		System.out.println("Login account service : " + accountService);
		
		AccountCreateDTO createDTO = new AccountCreateDTO("Đinh Đăng Khoa", "0937602105", "dinhdangkhoa0201@gmail.com", "11111111", "11111111");
		try {
			if(!accountService.findByEmail(createDTO.getEmail()).isPresent()) {
				Account account = accountService.createAdmin(createDTO);
				System.out.println("Admin : " + account);
			}
			//init();
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
}
