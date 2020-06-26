package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "sale_order_header")
public class SaleOrderHeader implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "order_date")
	private LocalDateTime orderDate;
	
	@Column(name = "ship_date")
	private LocalDateTime shipDate;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String note;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE
	})
	@JoinColumn(name = "account_id")
	private Account account;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private List<SaleOrderDetail> orderDetails;
	
	private LocalDateTime modifiedDate;
	
	private double totalOrder;
	@Column(columnDefinition = "nvarchar(50)")
	private String status;

	public SaleOrderHeader(int orderId, LocalDateTime orderDate, LocalDateTime shipDate,
			String note) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.note = note;
		this.modifiedDate = LocalDateTime.now();
	}

	public SaleOrderHeader(Account account, LocalDateTime orderDate, LocalDateTime shipDate, String note) {
		this.account = account;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.note = note;
		this.modifiedDate = LocalDateTime.now();
		this.status = "Chờ xử lý";
	}
	
	public SaleOrderHeader() {
	}

	public int getOrderId() {
		return orderId;
	}

	public Account getAccount() {
		return account;
	}

	public List<SaleOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public LocalDateTime getShipDate() {
		return shipDate;
	}

	public String getNote() {
		return note;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setOrderDetails(List<SaleOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public void setShipDate(LocalDateTime shipDate) {
		this.shipDate = shipDate;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public double getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(double totalOrder) {
		this.totalOrder = totalOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", account=" + account.toString() + ", orderDetails=" + orderDetails + ", orderDate="
				+ orderDate + ", shipDate=" + shipDate + ", note=" + note + "]";
	}
	
	
}
