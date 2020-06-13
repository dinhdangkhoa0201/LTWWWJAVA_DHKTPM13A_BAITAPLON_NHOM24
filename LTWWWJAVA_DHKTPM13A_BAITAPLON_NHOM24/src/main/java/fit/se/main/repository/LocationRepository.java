package fit.se.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se.main.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{
	Optional<Location> findByLocationName(String name); 
	
}
