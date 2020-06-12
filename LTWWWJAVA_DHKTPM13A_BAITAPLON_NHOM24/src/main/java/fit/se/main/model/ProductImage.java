package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_image")
public class ProductImage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	private String image;
	
	private LocalDateTime modifiedDate;

	public ProductImage(Product product, String image) {
		this.product = product;
		this.image = image;
		this.modifiedDate = LocalDateTime.now();
	}

	public ProductImage() {
	}

	public int getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public String getImage() {
		return image;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
