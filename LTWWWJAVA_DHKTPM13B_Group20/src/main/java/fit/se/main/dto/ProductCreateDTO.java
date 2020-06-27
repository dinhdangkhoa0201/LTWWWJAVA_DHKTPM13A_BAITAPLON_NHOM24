package fit.se.main.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ProductCreateDTO {
	private int productId;
	
	private String productName;
	
	private double price;
	
	private double sellingPrice;
	
	private int quantity;
	
	private int category;
	
	private int supplier;
	
	private int unitmeasure;
	
	private String note;
	
	private CommonsMultipartFile image;

	public ProductCreateDTO() {

	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getNote() {
		return note;
	}

	public CommonsMultipartFile getImage() {
		return image;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSellingPrice(double sellingProce) {
		this.sellingPrice = sellingProce;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setImage(CommonsMultipartFile image) {
		this.image = image;
	}

	public int getCategory() {
		return category;
	}

	public int getSupplier() {
		return supplier;
	}

	public int getUnitmeasure() {
		return unitmeasure;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}

	public void setUnitmeasure(int unitmeasure) {
		this.unitmeasure = unitmeasure;
	}

	@Override
	public String toString() {
		return "ProductCreateDTO [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", sellingPrice=" + sellingPrice + ", quantity=" + quantity + ", category=" + category + ", supplier="
				+ supplier + ", unitmeasure=" + unitmeasure + ", note=" + note + ", image=" + image + "]";
	}

	
	
}
