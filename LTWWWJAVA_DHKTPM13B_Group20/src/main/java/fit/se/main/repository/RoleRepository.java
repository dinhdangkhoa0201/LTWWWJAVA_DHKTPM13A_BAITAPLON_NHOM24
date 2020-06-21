package fit.se.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fit.se.main.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	@Query(value = "select * from role where rolename = ?1", nativeQuery = true)
	Role findByName(String roleName);
}
