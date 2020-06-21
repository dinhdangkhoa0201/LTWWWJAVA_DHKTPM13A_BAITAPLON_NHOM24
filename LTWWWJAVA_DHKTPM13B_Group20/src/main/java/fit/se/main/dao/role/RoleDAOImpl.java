package fit.se.main.dao.role;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fit.se.main.model.Role;
import fit.se.main.repository.RoleRepository;

@Repository
public class RoleDAOImpl implements RoleDAO{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findById(int id) {
		return roleRepository.getOne(id);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role create(Role entity) {
		return roleRepository.save(entity);
	}

	@Override
	public Role update(Role entity) {
		return roleRepository.save(entity);
	}

	@Override
	public void delete(Role entity) {
		roleRepository.delete(entity);
	}

	@Override
	public void deleteById(int entityId) {
		roleRepository.deleteById(entityId);
	}

	@Override
	public Role findByName(String rolename) {
		return roleRepository.findByName(rolename);
	}

}
