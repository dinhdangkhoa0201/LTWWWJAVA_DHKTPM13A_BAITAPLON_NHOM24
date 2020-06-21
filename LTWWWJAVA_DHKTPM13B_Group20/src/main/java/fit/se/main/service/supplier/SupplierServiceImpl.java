package fit.se.main.service.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.dao.supplier.SupplierDAO;
import fit.se.main.model.Supplier;

@Service
@Transactional
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

	@Override
	public void deleteById(int supplierId) {
		supplierDAO.deleteById(supplierId);
	}

	@Override
	public Supplier findById(int id) {
		return supplierDAO.findById(id);
	}

}
