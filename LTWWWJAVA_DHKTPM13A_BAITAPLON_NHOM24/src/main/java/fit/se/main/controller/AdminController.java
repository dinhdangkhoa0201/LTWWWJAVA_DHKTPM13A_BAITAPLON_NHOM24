package fit.se.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fit.se.main.dto.AccountCreateDTO;
import fit.se.main.model.Account;
import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.ProductImage;
import fit.se.main.model.Supplier;
import fit.se.main.model.UnitMeasure;
import fit.se.main.service.account.AccountService;
import fit.se.main.service.category.CategoryService;
import fit.se.main.service.supplier.SupplierService;
import fit.se.main.service.unit_measure.UnitMeasureService;

@Controller
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UnitMeasureService unitMeasureService;
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/khachhang")
	public String khachHang(Model model) {
		List<Account> accounts = accountService.findAll();
		List<String> emails = new ArrayList<String>();
		List<String> phones = new ArrayList<String>();
		for(Account account: accounts) {
			emails.add(account.getEmail());
			phones.add(account.getPhone());
		}
 		model.addAttribute("accounts", accounts);
		model.addAttribute("account", new Account());
		model.addAttribute("accountCreateDTO", new AccountCreateDTO());
		model.addAttribute("emails", emails);
		model.addAttribute("phones", phones);
		
		return "/admin/khachhang";
	}
	
	@PostMapping("/khachhang")
	private String khachhang(Model model, @ModelAttribute("account") Account account, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi mẹ rồi");
		}
		try {
			Account temp = accountService.findById(account.getAccountId());
			
			temp.setAccountName(account.getAccountName());
			temp.setBirthday(account.getBirthday());
			temp.setGender(account.getGender());
			temp.setEmail(account.getEmail());
			temp.setPhone(account.getPhone());
			temp.setAddress(account.getAddress());
			temp.setNote(account.getNote());
			
			accountService.updateAccount(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/khachhang";
	}
	
	@PostMapping("/themKhachHang")
	private String themKhachhang(Model model, @ModelAttribute("accountCreateDTO") AccountCreateDTO accountCreateDTO, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi mẹ rồi");
		}
		try {
			accountCreateDTO.setRepeatpassword(accountCreateDTO.getPassword());
			Account account = accountService.createMember(accountCreateDTO);
			System.out.println(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/khachhang";
	}
	
	@GetMapping("/xoaKhachHang")
	private String xoaKhachHang(Model model, @RequestParam(name = "accountId") int id) {
		try {
			Account account = accountService.findById(id);
			account.clearRole();
			accountService.updateAccount(account);
			accountService.deteleById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/khachhang";
	}
	
	
	
	@GetMapping("/index")
	public String dashboard(Model model) {
		return "/admin/index";
	}
	
	@GetMapping("/hanghoa")
	public String indexLink(Model model) {
		return "/admin/sanpham";
	}
	
	@GetMapping("/thongtinkhachhang")
	private String chitietKhachHang(Model model, @RequestParam(name = "accountId") int id) {
		Account account = accountService.findById(id);
		System.out.println("- Accoutn : " +account);
		model.addAttribute("account", account);
		return "/admin/thongtinkhachhang";
	}
	
	@GetMapping("/sanpham")
	public String sanPham(Model model) {
		return "/admin/sanpham";
	}
	
	@GetMapping("/themsanpham")
	public String themsanpham(Model model) {
		List<Category> categories = categoryService.findAll();
		List<UnitMeasure> unitMeasures = unitMeasureService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
		model.addAttribute("product", new Product());
		model.addAttribute("productImage", new ProductImage());
		model.addAttribute("categories", categories);
		model.addAttribute("category", new Category());
		model.addAttribute("unitmeasures", unitMeasures);
		model.addAttribute("unitmeasure", new UnitMeasure());
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("supplier", new Supplier());
		model.addAttribute("action", "");
		return "/admin/themsanpham";
	}
	/*
	 *	Danh muc San pham 
	 */
	@PostMapping("/themsanpham/themDanhMucSanPhamLuu")
	public String themDanhMucSanPhamLuu(Model model, @ModelAttribute(name = "category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them danh muc rồi");
		}
		categoryService.createCategory(category);
		return "redirect:/admin/themsanpham";
	}
	
	@PostMapping("/themsanpham/themDanhMucSanPhamLuuVaTiepTuc")
	public String themDanhMucSanPhamLuuVaTiepTuc(Model model, @ModelAttribute(name = "category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them danh muc rồi");
		}
		categoryService.createCategory(category);
		return "redirect:/admin/themsanpham#modalDanhMucSanPham";
	}
	
	/*
	 * 	Don vi tinh
	 */
	
	@PostMapping("/themsanpham/themDonViTinhLuu")
	public String themDonViTinhLuu(Model model, @ModelAttribute(name = "unitmeasure") UnitMeasure unitMeasure, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them don vi tinh rồi");
		}
		unitMeasureService.createUnitMeasure(unitMeasure);
		return "redirect:/admin/themsanpham";
	}
	@PostMapping("/themsanpham/themDonViTinhLuuVaTiepTuc")
	public String themDonViTinhLuuVaTiepTuc(Model model, @ModelAttribute(name = "unitmeasure") UnitMeasure unitMeasure, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them don vi tinh rồi");
		}
		unitMeasureService.createUnitMeasure(unitMeasure);
		return "redirect:/admin/themsanpham#modalDonViTinh";
	}
	
	/*
	 * 	Nha san xuat
	 */
	
	@PostMapping("/themsanpham/themNhaSanXuatLuu")
	public String themSanPhamLuu(Model model, @ModelAttribute(name = "supplier") Supplier supplier, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi mẹ rồi");
		}
		supplierService.createSupplier(supplier);
		return "redirect:/admin/themsanpham";
	}
	@PostMapping("/themsanpham/themNhaSanXuatLuuVaTiepTuc")
	public String themSanPhamLuuVaTiepTuc(Model model, @ModelAttribute(name = "supplier") Supplier supplier, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi mẹ rồi");
		}
		supplierService.createSupplier(supplier);
		return "redirect:/admin/themsanpham#modalNhaSanXuat";
	}
	
	@GetMapping("/banhang")
	public String banHang(Model model) {
		return "/admin/banhang";
	}
	
	@PostMapping("/thongtinkhachhang")
	private String thongtinkhachhang(Model model, @Valid Account account, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi mẹ rồi");
		}
		try {
			Account temp = accountService.findById(account.getAccountId());
			
			temp.setAccountName(account.getAccountName());
			temp.setBirthday(account.getBirthday());
			temp.setGender(account.getGender());
			temp.setEmail(account.getEmail());
			temp.setPhone(account.getPhone());
			temp.setAddress(account.getAddress());
			temp.setPassword(account.getPassword());
			temp.setNote(account.getNote());
			
			accountService.updateAccount(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/thongtinkhachhang?accountId=" + account.getAccountId() +"";
	}

}
