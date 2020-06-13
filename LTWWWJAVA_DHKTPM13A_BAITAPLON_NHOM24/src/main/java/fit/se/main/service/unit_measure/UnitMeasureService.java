package fit.se.main.service.unit_measure;

import java.util.List;

import fit.se.main.dto.UnitMeasureCreateDTO;
import fit.se.main.model.UnitMeasure;

public interface UnitMeasureService {
	public void createUnitMeasure(UnitMeasure unitMeasure);
	public void updateUnitMeasure(UnitMeasure unitMeasure);
	public void deleteUnitMeasure(UnitMeasure unitMeasure);
	public List<UnitMeasure> findAll();
	public UnitMeasure findById(int id);
	public UnitMeasure findByName(String name);
	public void deleteById(int unitId);
}
