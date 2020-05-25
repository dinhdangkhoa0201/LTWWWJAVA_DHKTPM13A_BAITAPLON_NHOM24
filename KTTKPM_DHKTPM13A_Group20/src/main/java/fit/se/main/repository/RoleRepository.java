package fit.se.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fit.se.main.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
//	@Query(value = "select * from role where name = ?0", nativeQuery = true)
	Optional<Role> findByName(String name);
}
