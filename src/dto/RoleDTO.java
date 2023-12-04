package dto;

public class RoleDTO {

	private int roleCode;
	private String roleName;
	private String roleDescription;
	
	public RoleDTO(int roleCode, String roleName, String roleDescription) {
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}

	public int getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(int roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
}
