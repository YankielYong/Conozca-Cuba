package dto;

public class UserDTO {

	private int user_code;
	private String user_name;
	private String user_nick;
	private String user_password;
	private int role_code;
	
	public UserDTO(int user_code, String user_name, String user_nick, String user_password, int role_code) {
		this.user_code = user_code;
		this.user_name = user_name;
		this.user_nick = user_nick;
		this.user_password = user_password;
		this.role_code = role_code;
	}

	public int getUser_code() {
		return user_code;
	}

	public void setUser_code(int user_code) {
		this.user_code = user_code;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_nick() {
		return user_nick;
	}

	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public int getRole_code() {
		return role_code;
	}

	public void setRole_code(int role_code) {
		this.role_code = role_code;
	}
}
