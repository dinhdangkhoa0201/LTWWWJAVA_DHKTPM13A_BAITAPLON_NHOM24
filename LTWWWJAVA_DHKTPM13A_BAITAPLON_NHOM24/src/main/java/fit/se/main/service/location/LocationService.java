package fit.se.main.service.location;

import java.util.List;

import fit.se.main.model.Location;

public interface LocationService {
	public void createLocation(Location location);
	public void updateLocation(Location location);
	public void deleteLocation(Location location);
	
	public List<Location> findAll();
}
