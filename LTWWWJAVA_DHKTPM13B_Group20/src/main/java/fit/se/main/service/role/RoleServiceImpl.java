package fit.se.main.service.role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fit.se.main.dao.role.RoleDAO;
import fit.se.main.model.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public Role findById(int id) {
		return roleDAO.findById(id);
	}

	@Override
	public Role create(Role role) {
		return roleDAO.create(role);
	}

	@Override
	public Role findByName(String name) {
		return roleDAO.findByName(name);
	}

}
