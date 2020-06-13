package fit.se.main.dto;

public class ProductUpdateDTO {
	private String productId;
	
	private String productName;
	
	private double price;
	
	private double sellingPrice;
	
	private String quantity;
	
	private int category;
	
	private int supplier;
	
	private int unitmeasure;
	
	private String note;
	
	private String image;

	public String getProductId() {
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

	public String getQuantity() {
		return quantity;
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

	public String getNote() {
		return note;
	}

	public String getImage() {
		return image;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

	public void setNote(String note) {
		this.note = note;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
