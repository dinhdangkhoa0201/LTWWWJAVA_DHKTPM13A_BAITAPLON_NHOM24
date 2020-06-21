package fit.se.main.dao.unit_measure;

import fit.se.main.dao.IOperations;
import fit.se.main.model.UnitMeasure;

public interface UnitMeasureDAO extends IOperations<UnitMeasure>{
	UnitMeasure findByUnitName(String unitName);
	UnitMeasure findById(int id);
}
