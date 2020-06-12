package fit.se.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

}
