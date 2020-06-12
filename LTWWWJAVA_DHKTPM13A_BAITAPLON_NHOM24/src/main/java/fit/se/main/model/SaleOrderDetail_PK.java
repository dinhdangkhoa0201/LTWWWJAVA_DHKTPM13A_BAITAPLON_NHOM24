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
	
	
}
