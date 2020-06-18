package fit.se.main.model;

import java.io.Serializable;

public class SaleOrderDetail_PK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int order;
	private int product;

	public SaleOrderDetail_PK(int order, int product) {
		this.order = order;
		this.product = product;
	}

	public SaleOrderDetail_PK() {
	}

	public int getOrder() {
		return order;
	}

	public int getProduct() {
		return product;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setProduct(int product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderDetail_PK [order=" + order + ", product=" + product + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + order;
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
		SaleOrderDetail_PK other = (SaleOrderDetail_PK) obj;
		if (order != other.order)
			return false;
		if (product != other.product)
			return false;
		return true;
	}
	
	
}
