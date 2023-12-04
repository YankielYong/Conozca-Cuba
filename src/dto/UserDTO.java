package dto;

public class UserDTO {

	private int userCode;
	private String userName;
	private String userNick;
	private String userPassword;
	private int roleCode;
	
	public UserDTO(int userCode, String userName, String userNick, String userPassword, int roleCode) {
		this.userCode = userCode;
		this.userName = userName;
		this.userNick = userNick;
		this.userPassword = userPassword;
		this.roleCode = roleCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(int roleCode) {
		this.roleCode = roleCode;
	}
}
