package fit.se.main.dao.location;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.Location;
import fit.se.main.repository.LocationRepository;

@Repository
public class LocationDAOImpl implements LocationDAO{

	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public Location findById(int id) {
		return locationRepository.getOne(id);
	}

	@Override
	public List<Location> findAll() {
		return locationRepository.findAll();
	}

	@Override
	public Location create(Location entity) {
		return locationRepository.save(entity);
	}

	@Override
	public Location update(Location entity) {
		return locationRepository.save(entity);
	}

	@Override
	public void delete(Location entity) {
		locationRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		locationRepository.deleteById(entityId);
	}

	@Override
	public Optional<Location> findByName(String name) {
		return locationRepository.findByLocationName(name);
	}

}
