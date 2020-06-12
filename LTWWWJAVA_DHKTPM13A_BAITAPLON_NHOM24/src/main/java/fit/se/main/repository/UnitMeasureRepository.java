package fit.se.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.UnitMeasure;

public interface UnitMeasureRepository extends JpaRepository<UnitMeasure, Integer>{
	UnitMeasure findByUnitName(String unitName);
}
