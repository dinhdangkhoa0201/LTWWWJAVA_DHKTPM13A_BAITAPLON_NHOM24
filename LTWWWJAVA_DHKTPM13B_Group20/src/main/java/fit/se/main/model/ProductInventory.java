package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "product_inventory")
@IdClass(ProductInventory_PK.class)
public class ProductInventory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	private int quantity;
	
	private LocalDateTime modifiedDate;

	public ProductInventory(Product product, Location location, int quantity) {
		this.product = product;
		this.location = location;
		this.quantity = quantity;
		this.modifiedDate = LocalDateTime.now();
	}

	public ProductInventory() {
	}

	public Product getProduct() {
		return product;
	}

	public Location getLocation() {
		return location;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "ProductInventory [product=" + product + ", location=" + location + ", quantity=" + quantity + "]";
	}
	
	
}
