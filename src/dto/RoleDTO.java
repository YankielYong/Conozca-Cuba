package dto;

public class RoleDTO {

	private int role_code;
	private String role_name;
	private String role_description;
	
	public RoleDTO(int role_code, String role_name, String role_description) {
		this.role_code = role_code;
		this.role_name = role_name;
		this.role_description = role_description;
	}

	public int getRole_code() {
		return role_code;
	}

	public void setRole_code(int role_code) {
		this.role_code = role_code;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_description() {
		return role_description;
	}

	public void setRole_description(String role_description) {
		this.role_description = role_description;
	}
}
