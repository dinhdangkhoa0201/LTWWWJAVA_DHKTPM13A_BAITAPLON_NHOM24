package fit.se.main.dto;

public class SupplierCreateDTO {
	private int supplierId;
	private String supplierName;
	public SupplierCreateDTO(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	@Override
	public String toString() {
		return "SupplierCreateDTO [supplierId=" + supplierId + ", supplierName=" + supplierName + "]";
	}
	
	
}
