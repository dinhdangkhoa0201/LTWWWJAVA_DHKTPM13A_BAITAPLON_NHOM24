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
	private LocalDate orderDate;
	
	@Column(name = "ship_date")
	private LocalDate shipDate;
	
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

	public SaleOrderHeader(int orderId, LocalDate orderDate, LocalDate shipDate,
			String note) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.note = note;
		this.modifiedDate = LocalDateTime.now();
	}

	public SaleOrderHeader(Account account, LocalDate orderDate, LocalDate shipDate, String note) {
		this.account = account;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.note = note;
		this.modifiedDate = LocalDateTime.now();
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

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public LocalDate getShipDate() {
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

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public void setShipDate(LocalDate shipDate) {
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

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", account=" + account + ", orderDetails=" + orderDetails + ", orderDate="
				+ orderDate + ", shipDate=" + shipDate + ", note=" + note + "]";
	}
	
	
}
