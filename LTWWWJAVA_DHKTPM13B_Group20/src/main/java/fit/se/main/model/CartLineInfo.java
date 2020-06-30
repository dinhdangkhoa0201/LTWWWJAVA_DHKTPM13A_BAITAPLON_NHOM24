package fit.se.main.model;

import java.util.Arrays;

public class CartLineInfo {
	private int iproductid;
	private String strProductName;
	private double dbSellingPrice;
	private int iQuantityinCart;
	private double totalInCat;
	private Supplier supplier;
	private Category category;
	private String bImage;
	public int getIproductid() {
		return iproductid;
	}
	public void setIproductid(int iproductid) {
		this.iproductid = iproductid;
	}
	public String getStrProductName() {
		return strProductName;
	}
	public void setStrProductName(String strProductName) {
		this.strProductName = strProductName;
	}
	public double getDbSellingPrice() {
		return dbSellingPrice;
	}
	public void setDbSellingPrice(double dbSellingPrice) {
		this.dbSellingPrice = dbSellingPrice;
	}
	public int getiQuantityinCart() {
		return iQuantityinCart;
	}
	public void setiQuantityinCart(int iQuantityinCart) {
		this.iQuantityinCart = iQuantityinCart;
	}
	public double getTotalInCat() {
		return totalInCat;
	}
	public void setTotalInCat(double totalInCat) {
		this.totalInCat = totalInCat;
	}
	public String getbImage() {
		return bImage;
	}
	public void setbImage(String bImage) {
		this.bImage = bImage;
	}
	
	public CartLineInfo(int iproductid, String strProductName, double dbSellingPrice, int iQuantityinCart,
			double totalInCat, Supplier supplier, Category category, String bImage) {
		super();
		this.iproductid = iproductid;
		this.strProductName = strProductName;
		this.dbSellingPrice = dbSellingPrice;
		this.iQuantityinCart = iQuantityinCart;
		this.totalInCat = totalInCat;
		this.supplier = supplier;
		this.category = category;
		this.bImage = bImage;
	}
	public CartLineInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProductCart [iproductid=" + iproductid + ", strProductName=" + strProductName + ", dbSellingPrice="
				+ dbSellingPrice + ", iQuantityinCart=" + iQuantityinCart + ", totalInCat=" + totalInCat + ", bImage="
				+ bImage + "]";
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
