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
import fit.se.main.principal.AccountPricipal;
import fit.se.main.service.account.AccountService;
import fit.se.main.service.category.CategoryService;
import fit.se.main.service.product.ProductService;
import fit.se.main.service.sale_order_detail.SaleOrderDetailService;
import fit.se.main.service.sale_order_header.SaleOrderHeaderService;

@Controller
@RequestMapping("/")

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
	


	@GetMapping("/sign-in")
	public String login(Model model, HttpServletRequest request) {
		AccountCreateDTO createDTO = new AccountCreateDTO("Đinh Đăng Khoa", "0937602105", "dinhdangkhoa0201@gmail.com", "123456789", "123456789");
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


	@GetMapping("/index")
	public String index(Model model, Principal principal) {
		AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
		if(accountAdminPricipal != null) {
			Account account = accountService.findById(accountAdminPricipal.getAccountId());
			model.addAttribute("account", account);
//
//			List<AccountRole> accountRoles = account.getAccountRoles();
//			for(AccountRole accountRole: accountRoles) {
//				if(accountRole.getRole().getName().equalsIgnoreCase("ROLE_ADMIN")) {
//					model.addAttribute("ROLE_ADMIN", accountRole.getRole().getName());
//					break;
//				}
//			}

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

	public void init() {		
		try {
			AccountCreateDTO accountMember = new AccountCreateDTO("ddk", "0937602105", "khoacyruss@gmail.com", "123456789", "123456789");
			Account account = null;
			if(!accountService.findByEmail(accountMember.getEmail()).isPresent()) {
				account = accountService.createMember(accountMember);
			} else {
				account = accountService.findById(2);
			}
			Category category = new Category("A");
			categoryService.createCategory(category);

			// List Product
			Product product1 = new Product("Gạo", 20000);
			product1.setCategory(category);
			Product product2 = new Product("Sua", 18000);
			product2.setCategory(category);
			productService.createProduct(product1);
			productService.createProduct(product2);

			SaleOrderHeader orderHeader = new SaleOrderHeader(account, LocalDate.now(), LocalDate.now(), "");
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
