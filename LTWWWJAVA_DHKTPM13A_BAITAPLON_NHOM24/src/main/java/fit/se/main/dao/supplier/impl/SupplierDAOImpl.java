package fit.se.main.dao.supplier.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.dao.supplier.SupplierDAO;
import fit.se.main.model.Supplier;
import fit.se.main.repository.SupplierRepository;

@Repository
public class SupplierDAOImpl implements SupplierDAO{

	@Autowired
	private SupplierRepository supplierRepository;
	@Override
	public Supplier findById(int id) {
		return supplierRepository.getOne(id);
	}

	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier create(Supplier entity) {
		return supplierRepository.save(entity);
	}

	@Override
	public Supplier update(Supplier entity) {
		return supplierRepository.save(entity);
	}

	@Override
	public void delete(Supplier entity) {
		supplierRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		supplierRepository.deleteById(entityId);
	}

}
