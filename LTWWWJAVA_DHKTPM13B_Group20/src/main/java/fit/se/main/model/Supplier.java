package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "supplier")
public class Supplier implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private int supplierId;
	
	@Column(name = "supplier_name", nullable = false,columnDefinition = "nvarchar(50)")
	private String supplierName;
	
	private String phone;
	private String address;
	private String email;
	private LocalDateTime modifiedDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", cascade = CascadeType.ALL)
	private List<Product> products;

	public Supplier(int supplierId, String supplierName, String phone, String address, String email) {
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.modifiedDate = LocalDateTime.now();
	}
	
	public Supplier(String supplierName, String phone, String address, String email) {
		this.supplierName = supplierName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.modifiedDate = LocalDateTime.now();
	}

	public Supplier(String supplierName) {
		this.supplierName = supplierName;
		this.modifiedDate = LocalDateTime.now();
	}

	public Supplier(int supplierId) {
		this.supplierId = supplierId;
		this.modifiedDate = LocalDateTime.now();
	}

	public Supplier() {
		this.modifiedDate = LocalDateTime.now();
	}

	public int getSupplierId() {
		return supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", phone=" + phone
				+ ", address=" + address + ", email=" + email + "]";
	}
	
}
