package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_order_header")
public class PurchaseOrderHeader implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private LocalDateTime orderDate;
	private LocalDateTime shipDate;
	private double subTotal;
	private LocalDateTime modifiedDate;
	

	public PurchaseOrderHeader(int orderId, LocalDateTime orderDate, LocalDateTime shipDate, double subTotal) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.subTotal = subTotal;
		this.modifiedDate = LocalDateTime.now();
	}

	public PurchaseOrderHeader(LocalDateTime orderDate, LocalDateTime shipDate, double subTotal) {
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.subTotal = subTotal;
		this.modifiedDate = LocalDateTime.now();
	}

	public PurchaseOrderHeader() {
	}

	public int getOrderId() {
		return orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public LocalDateTime getShipDate() {
		return shipDate;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public void setShipDate(LocalDateTime shipDate) {
		this.shipDate = shipDate;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "PurchaseOrderHeader [orderId=" + orderId + ", orderDate=" + orderDate + ", shipDate=" + shipDate
				+ ", subTotal=" + subTotal + "]";
	}
	
	
}
