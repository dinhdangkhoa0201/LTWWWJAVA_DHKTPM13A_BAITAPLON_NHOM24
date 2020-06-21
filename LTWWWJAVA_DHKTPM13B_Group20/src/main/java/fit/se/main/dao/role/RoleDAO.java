package fit.se.main.dao.role;

import java.util.List;

import fit.se.main.dao.IOperations;
import fit.se.main.model.Role;

public interface RoleDAO extends IOperations<Role>{
	Role findByName(String rolename);
}
