package fit.se.main.dao.unit_measure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.UnitMeasure;
import fit.se.main.repository.UnitMeasureRepository;

@Repository
public class UnitMeasureDAOImpl implements UnitMeasureDAO{

	@Autowired
	private UnitMeasureRepository unitMeasureRepository;
	
	@Override
	public UnitMeasure findById(int id) {
		return unitMeasureRepository.getOne(id);
	}

	@Override
	public List<UnitMeasure> findAll() {
		return unitMeasureRepository.findAll();
	}

	@Override
	public UnitMeasure create(UnitMeasure entity) {
		return unitMeasureRepository.save(entity);
	}

	@Override
	public UnitMeasure update(UnitMeasure entity) {
		return unitMeasureRepository.save(entity);
	}

	@Override
	public void delete(UnitMeasure entity) {
		unitMeasureRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		unitMeasureRepository.deleteById(entityId);
	}

	@Override
	public UnitMeasure findByUnitName(String unitName) {
		return unitMeasureRepository.findByUnitName(unitName);
	}

}
