package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int roleId;

	@Column(name = "rolename", nullable = false, columnDefinition = "nvarchar(50)")
	private String roleName;

	@Lob
	private String description;
	
	private LocalDateTime modifiedDate;
	
	@OneToMany(mappedBy = "roles")
	private List<Account> accountRoles;

	public Role() {
	}

	public Role(String rolename, String description) {
		this.roleName = rolename;
		this.description = description;
		this.modifiedDate = LocalDateTime.now();
	}

	public Role(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return roleName;
	}

	public void setName(String name) {
		this.roleName = name;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public List<Account> getAccountRoles() {
		return accountRoles;
	}

	public void setAccountRoles(List<Account> accountRoles) {
		this.accountRoles = accountRoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roleId;
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

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", description=" + description + "]";
	}
	
	
	
}
