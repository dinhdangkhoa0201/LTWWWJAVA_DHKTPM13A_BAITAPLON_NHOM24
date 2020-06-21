package fit.se.main.service.role;

import java.util.List;
import java.util.Optional;

import fit.se.main.model.Role;

public interface RoleService {
	Role findById(int id);
	Role findByName(String name);
	Role create(Role role);
}
