package fit.se.main.service.role;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.se.main.dao.role.RoleDAO;
import fit.se.main.model.Role;
import fit.se.main.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public Optional<Role> findById(Long id) {
		return roleDAO.findById(id);
	}

	@Override
	public Role create(Role role) {
		return roleDAO.create(role);
	}

	@Override
	public Optional<Role> findByName(String name) {
		return roleDAO.findByName(name);
	}

}
