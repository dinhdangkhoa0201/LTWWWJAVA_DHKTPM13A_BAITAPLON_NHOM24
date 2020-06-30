package fit.se.main.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fit.se.main.model.Account;
import fit.se.main.model.CartInfo;
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
public class DetailProductController {
	@Autowired
	private ProductService productService;

	@Autowired 
	private CategoryService categoryService;

	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/detailProduct")
	public String loadDetailProduct(Principal principal,Model model,@RequestParam (name = "product_id") int product_id,HttpServletRequest request) {
		HttpSession session  = request.getSession();
		Product product = productService.findById(product_id);
		CartInfo cartInfo = null;
		Object objCartInfo = session.getAttribute("myCart");
		if(objCartInfo !=null) {
			cartInfo = (CartInfo) objCartInfo;
		}else {
			cartInfo = new CartInfo();
			session.setAttribute("myCart",cartInfo);
		}
		model.addAttribute("product", product);
		model.addAttribute("myCart", cartInfo);
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
		return "detailProduct";
	}

}