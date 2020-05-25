package fit.se.main.dao.role;

import java.util.Optional;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Role;

public interface RoleDAO extends IOperations<Role>{
	Optional<Role> findByName(String name);
}
