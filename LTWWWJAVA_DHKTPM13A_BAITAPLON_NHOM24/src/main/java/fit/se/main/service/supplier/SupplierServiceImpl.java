package fit.se.main.service.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.se.main.dao.supplier.SupplierDAO;
import fit.se.main.model.Supplier;

@Service
public class SupplierServiceImpl implements SupplierService{

	@Autowired
	private SupplierDAO supplierDAO;
	@Override
	public void createSupplier(Supplier supplier) {
		supplierDAO.create(supplier);
	}

	@Override
	public void updateSupplier(Supplier supplier) {
		supplierDAO.update(supplier);
	}

	@Override
	public List<Supplier> findAll() {
		return supplierDAO.findAll();
	}

}
