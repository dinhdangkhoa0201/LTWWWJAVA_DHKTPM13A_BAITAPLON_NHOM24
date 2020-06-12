package fit.se.main.service.role;

import java.util.Optional;

import fit.se.main.model.Role;

public interface RoleService {
	Role findById(int id);
	Optional<Role> findByName(String name);
	Role create(Role role);
}
