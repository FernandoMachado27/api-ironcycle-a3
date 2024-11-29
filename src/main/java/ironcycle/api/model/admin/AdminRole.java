package ironcycle.api.model.admin;

public enum AdminRole {
	
	ADMIN("admin");
	
	private String role;
	
	AdminRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

}
