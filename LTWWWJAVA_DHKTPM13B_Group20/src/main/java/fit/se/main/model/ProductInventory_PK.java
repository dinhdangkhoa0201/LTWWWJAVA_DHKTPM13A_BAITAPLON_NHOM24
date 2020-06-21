package fit.se.main.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductInventory_PK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int location;
	private int product;
	public ProductInventory_PK(int location, int product) {
		this.location = location;
		this.product = product;
	}
	public ProductInventory_PK() {
	}
	public int getLocation() {
		return location;
	}
	public int getProduct() {
		return product;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + location;
		result = prime * result + product;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInventory_PK other = (ProductInventory_PK) obj;
		if (location != other.location)
			return false;
		if (product != other.product)
			return false;
		return true;
	}
	
	
}
