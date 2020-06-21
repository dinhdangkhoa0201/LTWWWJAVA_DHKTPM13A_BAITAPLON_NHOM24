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
@Table(name = "purchase_order_detail")
@IdClass(PurchaseOrderDetail_PK.class)
public class PurchaseOrderDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private PurchaseOrderHeader purchaseOrderHeader;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private int quantity;
	private double unitPrice;
	
	private LocalDateTime modifiedDate;
	
	public PurchaseOrderDetail(PurchaseOrderHeader purchaseOrderHeader, Product product, int quantity,
			double unitPrice) {
		this.purchaseOrderHeader = purchaseOrderHeader;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.modifiedDate = LocalDateTime.now();
	}
	
	public PurchaseOrderDetail() {
	}
	
	public PurchaseOrderHeader getPurchaseOrderHeader() {
		return purchaseOrderHeader;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader) {
		this.purchaseOrderHeader = purchaseOrderHeader;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "PuschaseOrderDetail [purchaseOrderHeader=" + purchaseOrderHeader + ", product=" + product
				+ ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
}
