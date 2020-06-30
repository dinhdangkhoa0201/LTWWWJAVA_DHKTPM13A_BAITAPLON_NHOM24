package fit.se.main.controller;

<<<<<<< HEAD
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
=======
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import fit.se.main.mail.Mail;
import fit.se.main.model.Account;
import fit.se.main.model.CartInfo;
import fit.se.main.model.CartLineInfo;
import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.Role;
import fit.se.main.model.SaleOrderDetail;
import fit.se.main.model.SaleOrderHeader;
import fit.se.main.model.Supplier;
import fit.se.main.principal.AccountPricipal;
import fit.se.main.service.account.AccountService;
import fit.se.main.service.category.CategoryService;
import fit.se.main.service.product.ProductService;
import fit.se.main.service.sale_order_detail.SaleOrderDetailService;
import fit.se.main.service.sale_order_header.SaleOrderHeaderService;
>>>>>>> vophan
import fit.se.main.service.supplier.SupplierService;
@Controller
public class PaymentInfoController {

	@Autowired
	private ProductService productService;
<<<<<<< HEAD
	
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
=======

	@Autowired 
	private CategoryService categoryService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SaleOrderDetailService saleOrderDetailService;
	
	@Autowired
	private SaleOrderHeaderService saleOrderHeaderService;
	
	@GetMapping("/OrderInfor")
	public String OrderInfor(HttpServletRequest request, Model model, Principal principal) {
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
		
		return "OrderInfor";
	}
	@GetMapping("/OrderInfor/Update")
	public String OrderInforUpdate(HttpServletRequest request, Model model, Principal principal, @RequestParam(name = "accountName")String accountName,
			@RequestParam(name = "phone")String phone, @RequestParam(name = "address")String address) throws Exception {
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
				account.setAccountName(accountName);
				account.setBirthday(account.getBirthday());
				account.setGender(account.getGender());
				account.setEmail(account.getEmail());
				account.setPhone(phone);
				account.setAddress(address);
				account.setNote(account.getNote());

				accountService.updateAccount(account);
				model.addAttribute("account", account);
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		
		return "redirect:/OrderInfor";
	}
	@GetMapping("/PayInfor")
	public String PayInfor(HttpServletRequest request, Model model, Principal principal) {
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
		model.addAttribute("localDate", LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		return "PayInfor";
	}
	@GetMapping("/PaySuccess")
	public String PaySuccess(HttpServletRequest request, Model model, Principal principal) {
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
				SaleOrderHeader orderHeader = new SaleOrderHeader(account, LocalDate.now(), LocalDate.now().plusDays(7), "", cartInfo.getTotal(), cartInfo.getTotal()<100000?19000:0);
				saleOrderHeaderService.createOrder(orderHeader);
				model.addAttribute("order_id", orderHeader.getOrderId());
				List<CartLineInfo> cartLineInfos = cartInfo.getCartLineItem();
				for (CartLineInfo cartLineInfo : cartLineInfos) {
					Product product = productService.findById(cartLineInfo.getIproductid());
					System.out.println(product);
					SaleOrderDetail orderDetail = new SaleOrderDetail(orderHeader, new Product(product.getProductId()), cartLineInfo.getiQuantityinCart(), cartLineInfo.getDbSellingPrice());
					saleOrderDetailService.createOrderDetail(orderDetail);
				}
						
				Mail mail = new Mail();
				mail.setFrom("managerhkv0201@gmail.com");
				mail.setTo(account.getEmail());
				mail.setSubject("HKV Thông báo đặt hàng");
			}
	    }
		else {
			model.addAttribute("account", null);
		}
		model.addAttribute("localDate", LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		session = null;
		return "PaySuccess";
	}
}
>>>>>>> vophan
