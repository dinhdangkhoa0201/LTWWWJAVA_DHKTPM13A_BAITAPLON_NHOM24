package fit.se.main.dao.location;

import java.util.Optional;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Location;

public interface LocationDAO extends IOperations<Location> {
	Optional<Location> findByName(String name);
}
