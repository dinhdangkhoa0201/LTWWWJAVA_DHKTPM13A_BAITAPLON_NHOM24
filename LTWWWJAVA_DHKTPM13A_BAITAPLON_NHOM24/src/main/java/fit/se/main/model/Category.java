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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "category_name", nullable = false, columnDefinition = "nvarchar(50)")
	private String categoryName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;
	
	private LocalDateTime modifiedDate;

	public Category(int categoryId, String categoryName, List<Product> products) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.products = products;
		this.modifiedDate = LocalDateTime.now();
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
		this.modifiedDate = LocalDateTime.now();
	}
	
	public Category(int categoryId) {
		this.categoryId = categoryId;
		this.modifiedDate = LocalDateTime.now();
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Category() {
		this.modifiedDate = LocalDateTime.now();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", products=" + products + "]";
	}
	
	
}
