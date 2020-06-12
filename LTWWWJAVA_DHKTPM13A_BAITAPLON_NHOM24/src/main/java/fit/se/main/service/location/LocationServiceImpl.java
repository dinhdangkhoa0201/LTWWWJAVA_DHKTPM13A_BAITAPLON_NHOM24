package fit.se.main.service.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.se.main.model.Location;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	private LocationService locationDAO;
	@Override
	public void createLocation(Location location) {
		locationDAO.createLocation(location);
	}

	@Override
	public void updateLocation(Location location) {
		locationDAO.updateLocation(location);
	}

	@Override
	public void deleteLocation(Location location) {
		locationDAO.deleteLocation(location);
	}

	@Override
	public List<Location> findAll() {
		return locationDAO.findAll();
	}
	
}
