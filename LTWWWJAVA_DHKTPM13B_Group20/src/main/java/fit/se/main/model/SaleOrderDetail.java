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
@Table(name = "sale_order_detail")
@IdClass(SaleOrderDetail_PK.class)
public class SaleOrderDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private SaleOrderHeader order;
	
	@Id
	@ManyToOne 
	@JoinColumn(name = "product_id")
	private Product product;
	
	private int quantity;
	
	private double unitPrice;
	
	private LocalDateTime modifiedDate;

	public SaleOrderDetail(SaleOrderHeader order, Product product, int quantity, double unitPrice) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.modifiedDate = LocalDateTime.now();
	}

	public SaleOrderDetail() {
	}

	public SaleOrderHeader getOrder() {
		return order;
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

	public void setOrder(SaleOrderHeader order) {
		this.order = order;
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
		return "OrderDetail [order=" + order + ", product=" + product + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + "]";
	}
	
	
}
