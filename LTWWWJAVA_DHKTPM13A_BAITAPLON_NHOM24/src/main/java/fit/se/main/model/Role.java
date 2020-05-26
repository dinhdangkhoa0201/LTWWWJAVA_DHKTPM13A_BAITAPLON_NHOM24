package fit.se.main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Long roleId;
	
	@Column(name = "rolename", nullable = false)
	private String roleName;
	
	@Lob
	private String description;
	
	@ManyToMany(mappedBy = "roles")
	private List<Account> accounts;

	public Role() {
	}
	
	public Role(String rolename, String description) {
		this.roleName = rolename;
		this.description = description;
		accounts = new ArrayList<Account>();
	}
	
	public Role(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Account> getKhachHangs() {
		return accounts;
	}

	public void setKhachHangs(List<Account> khachHangs) {
		this.accounts = khachHangs;
	}

	public String getName() {
		return roleName;
	}

	public void setName(String name) {
		this.roleName = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (roleId ^ (roleId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (roleId != other.roleId)
			return false;
		return true;
	}
}
