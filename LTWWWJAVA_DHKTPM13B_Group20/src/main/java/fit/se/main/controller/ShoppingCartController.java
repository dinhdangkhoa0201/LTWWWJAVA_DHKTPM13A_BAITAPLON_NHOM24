package fit.se.main.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import fit.se.main.dao.product.ProductDAO;
import fit.se.main.model.Account;
import fit.se.main.model.CartInfo;
import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Role;
import fit.se.main.model.Supplier;
import fit.se.main.principal.AccountPricipal;
import fit.se.main.model.CartLineInfo;
import fit.se.main.service.account.AccountService;
import fit.se.main.service.category.CategoryService;
import fit.se.main.service.product.ProductService;
import fit.se.main.service.supplier.SupplierService;

@Controller
public class ShoppingCartController {
	@Autowired
	private ProductService productService;

	@Autowired 
	private CategoryService categoryService;

	@Autowired 
	private SupplierService supplierService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/ShoppingCart")
	public String addToCart(Principal principal, HttpServletRequest request , Model model) {
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
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		return "ShoppingCart";

	}
	@GetMapping("/ShoppingCart/AddModel")
	public String addToCartModel(Principal principal, HttpServletRequest request , Model model, @RequestParam(name = "product_id") int product_id, @RequestParam(name = "quatityby") int quatityby) {
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
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		return "detailProduct";

	}

	@GetMapping("/ShoppingCart/Remove")
	private String deleteCart(Principal principal, HttpServletRequest request, Model model, @RequestParam(name = "iproductid") int iproductid) throws ServletException,IOException  {
		HttpSession session = request.getSession();
		CartInfo cartInfo = null;
		Object objCartInfo = session.getAttribute("myCart");
		if(objCartInfo !=null) {
			cartInfo = (CartInfo) objCartInfo;
		}else {
			cartInfo = new CartInfo();
			session.setAttribute("myCart",cartInfo);
		}	
		cartInfo.deleteCartItem(iproductid);
		model.addAttribute("myCart",cartInfo);
		if(principal!=null) {
	    	AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
			if(accountAdminPricipal != null) {
				Account account = accountService.findById(accountAdminPricipal.getAccountId());
				model.addAttribute("account", account);
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		return "redirect:/ShoppingCart";

	}



	@GetMapping("/ShoppingCart/UpdateProduct")
	private String PlusCart(Principal principal, HttpServletRequest request, Model model, @RequestParam(name = "iproductid") int iproductid, @RequestParam(name = "quantity") int quantity) throws ServletException,IOException  {
		HttpSession session = request.getSession();
		System.out.println("day la product idcccc"+iproductid);
		System.out.println("day la quantityccc"+quantity);
		Product product = productService.findById(iproductid);
		CartInfo cartInfo = null;
		Object objCartInfo = session.getAttribute("myCart");
		if(objCartInfo !=null) {
			cartInfo = (CartInfo) objCartInfo;
		}else {
			cartInfo = new CartInfo();
			session.setAttribute("myCart",cartInfo);
		}	
		CartLineInfo cartLineInfo ;//=cartLineInfo = new CartLineInfo(product.getProductId(), product.getProductName(), product.getPrice(), quantity, quantity*product.getPrice(),product.getSupplier(), product.getCategory() ,product.getImage());
		cartLineInfo = cartInfo.getCartLineInfo(iproductid);
		cartLineInfo = new CartLineInfo(product.getProductId(), product.getProductName(), product.getPrice(), quantity, quantity*product.getPrice(),product.getSupplier(), product.getCategory() ,product.getImage());
		cartInfo.updateProduct(cartLineInfo);
		model.addAttribute("myCart",cartInfo);
		if(principal!=null) {
	    	AccountPricipal accountAdminPricipal = (AccountPricipal)  ((Authentication) principal).getPrincipal();
			if(accountAdminPricipal != null) {
				Account account = accountService.findById(accountAdminPricipal.getAccountId());
				model.addAttribute("account", account);
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		return "ShoppingCart";
	}
}