package fit.se.main.controller;

import java.security.Principal;
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

import fit.se.main.mail.Mail;
import fit.se.main.model.Account;
import fit.se.main.model.CartInfo;
import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Role;
import fit.se.main.model.SaleOrderHeader;
import fit.se.main.model.Supplier;
import fit.se.main.principal.AccountPricipal;
import fit.se.main.service.account.AccountService;
import fit.se.main.service.category.CategoryService;
import fit.se.main.service.product.ProductService;
import fit.se.main.service.sale_order_header.SaleOrderHeaderService;
import fit.se.main.service.supplier.SupplierService;

@Controller
public class OrderController {
	@Autowired
	private SaleOrderHeaderService saleOrderHeaderService; 
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/order")
	public String loadDanhSacHoaDon(HttpServletRequest request,Principal principal, Model model) {
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
				List<SaleOrderHeader> orderHeaders = saleOrderHeaderService.findOrderByAccount(account);
				model.addAttribute("orderHeaders", orderHeaders);
			}

	    }
		else {
			model.addAttribute("account", null);
		}
	    return "order";
	}
	@GetMapping("/orderdetail")
	public String loadChiTietHoaDon(HttpServletRequest request,Principal principal, Model model, @RequestParam(name="order_id")int order_id) {
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
				SaleOrderHeader orderHeader = saleOrderHeaderService.findOrderById(order_id);
				model.addAttribute("order", orderHeader);
			}
	    }
		else {
			model.addAttribute("account", null);
		}
	    return "orderdetail";
	}
}
