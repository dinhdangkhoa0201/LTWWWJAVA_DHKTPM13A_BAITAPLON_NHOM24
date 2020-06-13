package fit.se.main.service.unit_measure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.se.main.dao.unit_measure.UnitMeasureDAO;
import fit.se.main.dto.UnitMeasureCreateDTO;
import fit.se.main.model.UnitMeasure;

@Service
public class UnitMeasureServiceImpl implements UnitMeasureService{
	
	@Autowired
	private UnitMeasureDAO unitMeasureDAO;
	@Override
	public void createUnitMeasure(UnitMeasure unitMeasure) {
		unitMeasureDAO.create(unitMeasure);
	}

	@Override
	public void updateUnitMeasure(UnitMeasure unitMeasure) {
		unitMeasureDAO.update(unitMeasure);
	}

	@Override
	public void deleteUnitMeasure(UnitMeasure unitMeasure) {
		unitMeasureDAO.delete(unitMeasure);
	}

	@Override
	public List<UnitMeasure> findAll() {
		return unitMeasureDAO.findAll();
	}

	@Override
	public UnitMeasure findById(int id) {
		return unitMeasureDAO.findById(id);
	}

	@Override
	public UnitMeasure findByName(String name) {
		return unitMeasureDAO.findByUnitName(name);
	}

	@Override
	public void deleteById(int unitId) {
		unitMeasureDAO.deleteById(unitId);
	}


}
