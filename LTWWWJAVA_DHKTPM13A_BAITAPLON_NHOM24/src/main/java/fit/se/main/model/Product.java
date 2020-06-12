package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "product_name", nullable = false, columnDefinition = "nvarchar(50)")
	private String productName;
	
	@Column(name = "price")
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product")
	private List<ProductInventory> productInventories;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<SaleOrderDetail> orderDetails;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private UnitMeasure unitMeasure;
	
	private LocalDateTime modifiedDate;
	
	public Product(int productId, String productName, double price, Category category) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.category = category;
		this.modifiedDate = LocalDateTime.now();
	}

	public Product(String productName, double price) {
		this.productName = productName;
		this.price = price;
		category = new Category();
		this.modifiedDate = LocalDateTime.now();
	}

	public Product(int productId) {
		this.productId = productId;
	}
	
	public Product() {
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

	public Category getCategory() {
		return category;
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

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ProductInventory> getProductInventories() {
		return productInventories;
	}

	public List<SaleOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setProductInventories(List<ProductInventory> productInventories) {
		this.productInventories = productInventories;
	}

	public void setOrderDetails(List<SaleOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public UnitMeasure getUnitMeasure() {
		return unitMeasure;
	}

	public void setUnitMeasure(UnitMeasure unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", category="
				+ category + "]";
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	
	
}
