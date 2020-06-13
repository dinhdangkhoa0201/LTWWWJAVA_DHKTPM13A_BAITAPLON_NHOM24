package fit.se.main.service.supplier;

import java.util.List;

import fit.se.main.model.Supplier;

public interface SupplierService {
	public void createSupplier(Supplier supplier);
	public void updateSupplier(Supplier supplier);
	public List<Supplier> findAll();
	public void deleteById(int supplierId);
	public Supplier findById(int id);
}
