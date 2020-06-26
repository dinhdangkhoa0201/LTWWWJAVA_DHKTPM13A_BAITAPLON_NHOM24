package fit.se.main.controller;

import java.time.LocalDateTime;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fit.se.main.dto.AccountCreateDTO;
import fit.se.main.dto.ProductCreateDTO;
import fit.se.main.model.Account;
import fit.se.main.model.Category;
import fit.se.main.model.Product;
import fit.se.main.model.ProductImage;
import fit.se.main.model.ProductInventory;
import fit.se.main.model.SaleOrderDetail;
import fit.se.main.model.SaleOrderHeader;
import fit.se.main.model.Supplier;
import fit.se.main.model.UnitMeasure;
import fit.se.main.service.account.AccountService;
import fit.se.main.service.category.CategoryService;
import fit.se.main.service.product.ProductService;
import fit.se.main.service.sale_order_header.SaleOrderHeaderService;
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

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SaleOrderHeaderService saleOrderHeaderService;
	
/* - Trang chủ Admin */
	@GetMapping("/index")
	public String dashboard(Model model) {
		return "/admin/index";
	}
	
/* - Danh sách Khách hàng */
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
/* - Cập nhật thông tin Khách hàng */
	@PostMapping("/khachhang")
	public String capNhatKhachHang(Model model, @ModelAttribute("account") Account account, BindingResult result) {
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
/* - Thêm Khách hàng */
	@PostMapping("/themKhachHang")
	public String themKhachhang(Model model, @ModelAttribute("accountCreateDTO") AccountCreateDTO accountCreateDTO, BindingResult result) {
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
/* - Xoá Khách hàng */
	@GetMapping("/xoaKhachHang")
	public String xoaKhachHang(Model model, @RequestParam(name = "accountId") int id) {
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

/* - Danh sách Sản phẩm */
	@GetMapping("/hanghoa")
	public String hanghoa(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		return "/admin/sanpham";
	}
/* - Thông tin Khách hàng */
	@GetMapping("/thongtinkhachhang")
	public String chitietKhachHang(Model model, @RequestParam(name = "accountId") int id) {
		Account account = accountService.findById(id);
		System.out.println("- Accoutn : " +account);
		model.addAttribute("account", account);
		return "/admin/thongtinkhachhang";
	}
/* - Danh sách Sản phẩm */
	@GetMapping("/sanpham")
	public String sanPham(Model model) {
		return "/admin/sanpham";
	}
/* - Thông tin Sản phẩm */
	@GetMapping(path = {"/sanpham/thongtinsanpham", "/sanpham/thongtinsanpham{productId}#**"})
	public String thongTinSanPham(Model model, @RequestParam(name = "productId") int productId) {
		Product product = productService.findById(productId);
		if(product != null) {
			System.out.println("- Thong tin san pham : " + product);
			
			List<Category> categories = categoryService.findAll();
			List<UnitMeasure> unitMeasures = unitMeasureService.findAll();
			List<Supplier> suppliers = supplierService.findAll();
			
			int id = product.getProductId();
			
			model.addAttribute("product", product);
			model.addAttribute("productId", id);
			model.addAttribute("productImage", new ProductImage());
			model.addAttribute("categories", categories);
			model.addAttribute("category", new Category(id));
			model.addAttribute("unitmeasures", unitMeasures);
			model.addAttribute("unitmeasure", new UnitMeasure(id));
			model.addAttribute("suppliers", suppliers);
			model.addAttribute("supplier", new Supplier(id));
			return "/admin/thongtinsanpham";
			
		}
		return "/admin/hanghoa";
	}
/* - Ngừng bán Sản phẩm */
	@GetMapping("/sanpham/stopSelling")
	public String ngungBanSanPham(Model model, @RequestParam(name = "productId") int productId) {
		Product product = productService.findById(productId);
		product.setEnable(false);
		productService.updateProduct(product);
		return "redirect:/admin/hanghoa";
	}
/* - Cho phép bán Sản phẩm */
	@GetMapping("/sanpham/continueToSell")
	public String banSanPham(Model model, @RequestParam(name = "productId") int productId) {
		Product product = productService.findById(productId);
		product.setEnable(true);
		productService.updateProduct(product);
		return "redirect:/admin/hanghoa";
	}
/* - Xoá sản phẩm, Nhưng không xoá được nếu Sản phẩm đó chưa Khoá Ngoại */
	@GetMapping("/sanpham/xoaSanPham")
	public String xoaSanPham(Model model, @RequestParam(name = "productId") int productId) {
		Product product = productService.findById(productId);
		if(product != null) {
			product.setCategory(new Category());
			product.setOrderDetails(new ArrayList<SaleOrderDetail>());
			product.setProductImages(new ArrayList<ProductImage>());
			product.setProductInventories(new ArrayList<ProductInventory>());
			product.setSupplier(new Supplier());
			product.setUnitMeasure(new UnitMeasure());
			productService.updateProduct(product);
			productService.deleteProduct(product);
			
		}
		return "redirect:/admin/sanpham";
	}
/* - Thêm sản phẩm */
	@GetMapping("/themsanpham")
	public String themsanpham(Model model) {
		List<Category> categories = categoryService.findAll();
		List<UnitMeasure> unitMeasures = unitMeasureService.findAll();
		List<Supplier> suppliers = supplierService.findAll();
		model.addAttribute("productCreateDTO", new ProductCreateDTO());
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
/* - Thêm sản phẩm Lưu và tiếp tục */
	@PostMapping("/themsanpham/themSanPhamLuuVaTiepTuc")
	public String themsanphamLuuVaTiepTuc(Model model, @ModelAttribute("product") ProductCreateDTO productCreateDTO, BindingResult result) {
		try {
			if(result.hasErrors()) {
				System.out.println("Loi them san pham Post");
			}
			System.out.println("1. Product Create DTO " + productCreateDTO);
			productService.createProduct(productCreateDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/themsanpham";
	}
/* - Thêm sản phẩm Lưu sau đó trở về danh sách Sản phẩm */
	@PostMapping("/themsanpham/themSanPhamLuu")
	public String themsanphamLuu(Model model, @ModelAttribute("product") ProductCreateDTO productCreateDTO, BindingResult result) {
		try {
			if(result.hasErrors()) {
				System.out.println("Loi them san pham Post");
			}
			System.out.println("1. Product Create DTO " + productCreateDTO);
			productService.createProduct(productCreateDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/hanghoa";
	}
	
/* ---------- START : Thêm sản phảm ---------- */

	/*
	 *	Danh muc San pham 
	 */
	@PostMapping("/themsanpham/themDanhMucSanPhamLuu")
	public String themSanPham_themDanhMucSanPhamLuu(Model model, @ModelAttribute(name = "category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them danh muc rồi");
		}
		categoryService.createCategory(category);
		return "redirect:/admin/themsanpham";
	}

	@PostMapping("/themsanpham/themDanhMucSanPhamLuuVaTiepTuc")
	public String themSanPham_themDanhMucSanPhamLuuVaTiepTuc(Model model, @ModelAttribute(name = "category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them danh muc rồi");
		}
		categoryService.createCategory(category);
		return "redirect:/admin/themsanpham#modalDanhMucSanPhamForm";
	}

	@GetMapping("/themsanpham/xoaDanhMucSanPham")
	public String themSanPham_xoaDanhMucSanPham(Model model, @RequestParam(name = "categoryId") int categoryId) {
		try {
			Category category = categoryService.findById(categoryId);
			if(category.getProducts().size() > 1) {
				return "redirect:/admin/themsanpham#danhMucSanPhamChuaSanPham";
			} else {
				categoryService.deleteCategory(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		categoryService.deleteById(categoryId);
		return "redirect:/admin/themsanpham#modalDanhMucSanPhamTable";
	}

	/*
	 * 	Don vi tinh
	 */

	@PostMapping("/themsanpham/themDonViTinhLuu")
	public String themSanPham_themDonViTinhLuu(Model model, @ModelAttribute(name = "unitmeasure") UnitMeasure unitMeasure, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them don vi tinh rồi");
		}
		unitMeasureService.createUnitMeasure(unitMeasure);
		return "redirect:/admin/themsanpham";
	}
	@PostMapping("/themsanpham/themDonViTinhLuuVaTiepTuc")
	public String themSanPham_themDonViTinhLuuVaTiepTuc(Model model, @ModelAttribute(name = "unitmeasure") UnitMeasure unitMeasure, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them don vi tinh rồi");
		}
		unitMeasureService.createUnitMeasure(unitMeasure);
		return "redirect:/admin/themsanpham#modalDonViTinhForm";
	}

	@GetMapping("/themsanpham/xoaDonViTinh")
	public String themSanPham_xoaDonViTinh(Model model, @RequestParam(name = "unitId") int unitId) {
		try {
			UnitMeasure unitMeasure = unitMeasureService.findById(unitId);
			if(unitMeasure.getProduct().size() > 0) {
				return "redirect:/admin/themsanpham#donViTinhChuaSanPham";
			} else {
				unitMeasureService.deleteUnitMeasure(unitMeasure);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		unitMeasureService.deleteById(unitId);
		return "redirect:/admin/themsanpham#modalDonViTinhTable";
	}

	/*
	 * 	Nha san xuat
	 */

	@PostMapping("/themsanpham/themNhaSanXuatLuu")
	public String themSanPham_themSanPhamLuu(Model model, @ModelAttribute(name = "supplier") Supplier supplier, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi mẹ rồi");
		}
		supplierService.createSupplier(supplier);
		return "redirect:/admin/themsanpham";
	}
	@PostMapping("/themsanpham/themNhaSanXuatLuuVaTiepTuc")
	public String themSanPham_themSanPhamLuuVaTiepTuc(Model model, @ModelAttribute(name = "supplier") Supplier supplier, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi mẹ rồi");
		}
		supplierService.createSupplier(supplier);
		return "redirect:/admin/themsanpham#modalNhaSanXuatForm";
	}

	@GetMapping("/themsanpham/xoaNhaSanXuat")
	public String themSanPham_xoaNhaSanXuat(Model model, @RequestParam(name = "supplierId") int supplierId) {
		try {
			Supplier supplier = supplierService.findById(supplierId);
			if(supplier.getProducts().size() > 0) {
				return "redirect:/admin/themsanpham#nhaSanXuatChuaSanPham";
			} else {
				supplierService.deleteById(supplier.getSupplierId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		supplierService.deleteById(supplierId);
		return "redirect:/admin/sanpham/themsanpham#modalNhaSanXuatTable";
	}
	
/* ---------- END : Thêm sản phảm ---------- */
	
/* ---------- START : Thong tin sản phảm ---------- */
	
	/*
	 *	Danh muc San pham 
	 */
	@PostMapping("/sanpham/thongtinsanpham/themDanhMucSanPhamLuu")
	public String thongTinSanPham_themDanhMucSanPhamLuu(Model model, @ModelAttribute(name = "category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them danh muc rồi");
		}
		System.out.println("CategoryCreateDTO : " + category);
		Category temp = new Category();
		temp.setCategoryName(category.getCategoryName());
		categoryService.createCategory(temp);
		return "redirect:/admin/sanpham/thongtinsanpham?productId="+ category.getCategoryId() +"";
	}
	
	@PostMapping("/sanpham/thongtinsanpham/themDanhMucSanPhamLuuVaTiepTuc")
	public String thongTinSanPham_themDanhMucSanPhamLuuVaTiepTuc(Model model, @ModelAttribute(name = "category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them danh muc rồi");
		}
		System.out.println("CategoryCreateDTO : " + category);
		Category temp = new Category();
		temp.setCategoryName(category.getCategoryName());
		categoryService.createCategory(temp);
		return "redirect:/admin/sanpham/thongtinsanpham?productId="+ category.getCategoryId() +"#modalDanhMucSanPhamForm";
	}
	
	@GetMapping("/sanpham/thongtinsanpham/xoaDanhMucSanPham")
	public String thongTinSanPham_xoaDanhMucSanPham(Model model, @RequestParam(name = "categoryId") int categoryId, @RequestParam(name = "productId") int productId) {
		try {
			Category category = categoryService.findById(categoryId);
			if(category.getProducts().size() > 1) {
				return "redirect:/admin/sanpham/thongtinsanpham?productId="+ productId +"#danhMucSanPhamChuaSanPham";
			} else {
				categoryService.deleteCategory(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/sanpham/thongtinsanpham?productId="+ productId +"#modalDanhMucSanPhamTable";
	}
	
	/*
	 * 	Don vi tinh
	 */
	
	@PostMapping("/sanpham/thongtinsanpham/themDonViTinhLuu")
	public String thongTinSanPham_themDonViTinhLuu(Model model, @ModelAttribute(name = "unitmeasure") UnitMeasure unitMeasure, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them don vi tinh rồi");
		}
		UnitMeasure temp = new UnitMeasure();
		temp.setUnitName(unitMeasure.getUnitName());
		unitMeasureService.createUnitMeasure(temp);
		return "redirect:/admin/sanpham/thongtinsanpham?productId=" + unitMeasure.getUnitId();
	}
	@PostMapping("/sanpham/thongtinsanpham/themDonViTinhLuuVaTiepTuc")
	public String thongTinSanPham_themDonViTinhLuuVaTiepTuc(Model model, @ModelAttribute(name = "unitmeasure") UnitMeasure unitMeasure, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi them don vi tinh rồi");
		}
		UnitMeasure temp = new UnitMeasure();
		temp.setUnitName(unitMeasure.getUnitName());
		unitMeasureService.createUnitMeasure(temp);
		return "redirect:/admin/sanpham/thongtinsanpham?productId="+ unitMeasure.getUnitId() +"#modalDonViTinhForm";
	}
	
	@GetMapping("/sanpham/thongtinsanpham/xoaDonViTinh")
	public String thongTinSanPham_xoaDonViTinh(Model model, @RequestParam(name = "unitId") int unitId, @RequestParam(name = "productId") int productId) {
		try {
			UnitMeasure unitMeasure = unitMeasureService.findById(unitId);
			if(unitMeasure.getProduct().size() > 0) {
				return "redirect:/admin/sanpham/thongtinsanpham?productId="+ productId +"#donViTinhChuaSanPham";
			} else {
				unitMeasureService.deleteUnitMeasure(unitMeasure);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/sanpham/thongtinsanpham?productId="+ productId +"#modalDonViTinhTable";
	}
	
	/*
	 * 	Nha san xuat
	 */
	
	@PostMapping("/sanpham/thongtinsanpham/themNhaSanXuatLuu")
	public String thongTinSanPham_themSanPhamLuu(Model model, @ModelAttribute(name = "supplier") Supplier supplier, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi mẹ rồi");
		}
		Supplier temp = new Supplier();
		temp.setSupplierName(supplier.getSupplierName());
		supplierService.createSupplier(temp);
		return "redirect:/admin/sanpham/thongtinsanpham?productId="+ supplier.getSupplierId();
	}
	@PostMapping("/sanpham/thongtinsanpham/themNhaSanXuatLuuVaTiepTuc")
	public String thongTinSanPham_themSanPhamLuuVaTiepTuc(Model model, @ModelAttribute(name = "supplier") Supplier supplier, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi mẹ rồi");
		}
		Supplier temp = new Supplier();
		temp.setSupplierName(supplier.getSupplierName());
		supplierService.createSupplier(temp);
		return "redirect:/admin/sanpham/thongtinsanpham?productId="+ supplier.getSupplierId() +"#modalNhaSanXuatForm";
	}
	
	@GetMapping("/sanpham/thongtinsanpham/xoaNhaSanXuat")
	public String thongTinSanPham_xoaNhaSanXuat(Model model, @RequestParam(name = "supplierId") int supplierId, @RequestParam(name = "productId") int productId) {
		try {
			Supplier supplier = supplierService.findById(supplierId);
			if(supplier.getProducts().size() > 0) {
				return "redirect:/admin/sanphamthongtinsanpham?productId="+ productId +"#nhaSanXuatChuaSanPham";
			} else {
				supplierService.deleteById(supplier.getSupplierId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/sanpham/thongtinsanpham?productId="+ productId +"#modalNhaSanXuatTable";
	}
	
	/* ---------- END : Thong tin sản phảm ---------- */

/* - Cập nhật sản phẩm - */
	@PostMapping("/sanpham/capNhatSanPham")
	public String capNhatSanPham(Model model, @ModelAttribute(name = "product") Product product, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Lỗi cap nhat San pham ");
		}
		System.out.println("Cap nhat Product : " + product);
		System.out.println("Categpry : " + product.getCategory());
		System.out.println("UnitMeasure : " + product.getUnitMeasure());
		System.out.println("Supplier : " + product.getSupplier());
		Product temp = productService.findById(product.getProductId());
		temp.setProductName(product.getProductName());
		temp.setPrice(product.getPrice());
		temp.setSellingPrice(product.getSellingPrice());
		temp.setQuantity(product.getQuantity());
		temp.setCategory(product.getCategory());
		temp.setSupplier(product.getSupplier());
		temp.setUnitMeasure(product.getUnitMeasure());
		temp.setNote(product.getNote());
		temp.setImage(product.getImage());
		temp.setModifiedDate(LocalDateTime.now());
		
		productService.updateProduct(temp);
		return "redirect:/admin/sanpham/thongtinsanpham?productId=" + product.getProductId();
	}

	@GetMapping("/banhang")
	public String banHang(Model model) {
		return "/admin/banhang";
	}

	@PostMapping("/thongtinkhachhang")
	public String thongtinkhachhang(Model model, @Valid Account account, BindingResult result) {
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

	@GetMapping("/hoadon")
	public String hoaDon(Model model) {
		List<SaleOrderHeader> saleOrderHeaders = new ArrayList<SaleOrderHeader>();
		saleOrderHeaders = saleOrderHeaderService.findAll();
		model.addAttribute("saleOrderHeaders",saleOrderHeaders);
		return "admin/hoadon";
	}
	
	@GetMapping("/hoadon/taodonhang")
	public String taoDonHang(Model model) {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		List<Account> accounts = accountService.findAll();
		List<Product> products = productService.findAll();
		String jsonAccount = gson.toJson(accounts.toString());
		String jsonProduct = gson.toJson(products.toString());
		model.addAttribute("accounts", jsonAccount);
		model.addAttribute("products", jsonProduct);
		
		SaleOrderHeader saleOrderHeader = new SaleOrderHeader();
		model.addAttribute("saleOrderHeader", saleOrderHeader);
		
		return "/admin/banhang";
	}
	
	@PostMapping("/hoadon/taohoadon")
	public String taoHoaDon(Model model, @ModelAttribute("saleOrderHeader") SaleOrderHeader saleOrderHeader, BindingResult result) {
		System.out.println("sale Order : " + saleOrderHeader);
		return "redirect:/admin/hoadon";
	}
	
	@GetMapping("/nhomsanpham")
	public String nhomSanPham(Model model) {
		List<Category> categories = new ArrayList<Category>();
		categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "admin/nhomsanpham";
	}
	
	@GetMapping("/nhacungcap")
	public String nhaCungCap(Model model) {
		List<Supplier> suppliers = supplierService.findAll();
		model.addAttribute("suppliers", suppliers);
		return "/admin/nhacungcap";
	}
	
	@GetMapping("/nhacungcap/thongtinnhacungcap")
	public String thongTinNhaCungCap(Model model, @RequestParam(name = "supplierId") int supplierId) {
		Supplier supplier = supplierService.findById(supplierId);
		model.addAttribute("supplier", supplier);
		return "/admin/thongtinnhacungcap";
	}
}
