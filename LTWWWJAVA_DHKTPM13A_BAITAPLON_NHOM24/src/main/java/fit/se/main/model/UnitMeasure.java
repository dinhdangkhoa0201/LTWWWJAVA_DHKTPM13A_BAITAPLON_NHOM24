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

@Entity
public class UnitMeasure implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_id")
	private int unitId;
	
	@Column(name = "unit_name", nullable = false, columnDefinition = "nvarchar(50)")
	private String unitName;
	
	private LocalDateTime modifiedDate;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMeasure", cascade = CascadeType.ALL)
	private List<Product> products;

	public UnitMeasure(int unitId, String unitName, LocalDateTime modifiedDate) {
		this.unitId = unitId;
		this.unitName = unitName;
		this.modifiedDate = modifiedDate;
	}

	public UnitMeasure(String unitName) {
		this.unitName = unitName;
		this.modifiedDate = LocalDateTime.now();
	}
	
	public UnitMeasure(int unitId) {
		this.unitId = unitId;
		this.modifiedDate = LocalDateTime.now();
	}

	public UnitMeasure() {
		this.modifiedDate = LocalDateTime.now();
	}

	public int getUnitId() {
		return unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<Product> getProduct() {
		return products;
	}

	public void setProduct(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "UnitMeasure [unitId=" + unitId + ", unitName=" + unitName + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
	
	
}
