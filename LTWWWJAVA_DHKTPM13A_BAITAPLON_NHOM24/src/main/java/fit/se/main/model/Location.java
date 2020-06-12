package fit.se.main.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private int id;
	
	@Column(name = "location_name", columnDefinition = "nvarchar(50)", nullable = false)
	private String locationName;
	
	@OneToMany(mappedBy = "location")
	private List<ProductInventory> productInventories;

	public Location(int id, String name) {
		this.id = id;
		this.locationName = name;
	}

	public Location(String name) {
		this.locationName = name;
	}

	public Location() {
	}

	public int getId() {
		return id;
	}

	public String getLocationName() {
		return locationName;
	}

	public List<ProductInventory> getProductInventories() {
		return productInventories;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setProductInventories(List<ProductInventory> productInventories) {
		this.productInventories = productInventories;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + locationName + "]";
	}
	
	
	
}
