package mx.com.tp.smc.entity;

public class User {

	private String username;
	private String name;
	private String organization;
	private String succes;
	private String mssg;
	private String role;
	private String createuser;
	private String usersnum;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public String getSucces() {
		return succes;
	}
	public void setSucces(String succes) {
		this.succes = succes;
	}
	
	public String getMssg() {
		return mssg;
	}
	public void setMssg(String mssg) {
		this.mssg = mssg;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getCreateuser() {
		return createuser;
	}
	
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getUsersnum() {
		return usersnum;
	}
	public void setUsersnum(String usersnum) {
		this.usersnum = usersnum;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [username=");
		builder.append(username);
		builder.append(", name=");
		builder.append(name);
		builder.append(", organization=");
		builder.append(organization);
		builder.append(", succes=");
		builder.append(succes);
		builder.append(", mssg=");
		builder.append(mssg);
		builder.append(", role=");
		builder.append(role);
		builder.append(", createuser=");
		builder.append(createuser);
		builder.append(", usersnum=");
		builder.append(usersnum);
		builder.append("]");
		return builder.toString();
	}
	
	
}
