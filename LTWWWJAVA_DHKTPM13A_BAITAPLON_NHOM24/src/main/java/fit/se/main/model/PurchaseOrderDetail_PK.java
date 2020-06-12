package fit.se.main.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PurchaseOrderDetail_PK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int purchaseOrderHeader;
	private int product;
	public PurchaseOrderDetail_PK(int purchaseOrderHeader, int product) {
		this.purchaseOrderHeader = purchaseOrderHeader;
		this.product = product;
	}
	public PurchaseOrderDetail_PK() {
	}
	public int getPurchaseOrderHeader() {
		return purchaseOrderHeader;
	}
	public int getProduct() {
		return product;
	}
	public void setPurchaseOrderHeader(int purchaseOrderHeader) {
		this.purchaseOrderHeader = purchaseOrderHeader;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "PuschaseOrderDetail_PK [purchaseOrderHeader=" + purchaseOrderHeader + ", product=" + product + "]";
	}
	
	
}
