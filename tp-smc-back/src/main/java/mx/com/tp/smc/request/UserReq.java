package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

public class UserReq {
	@NotNull
	@Size(min = 1, max = 255)
	private String username;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String password;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	@NotNull
	@Size(min = 1, max = 255)
	private String organization;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String organizationalDirection;
	
	@NotNull
	@Size(min = 1, max = 255)
	@Email
	private String email;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String telephone;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String image;
	
	@NotNull
	private Integer enabled;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String role;
	
	
	public String getLogin() {
		return username;
	}
	public void setLogin(String login) {
		this.username = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getOrganizationalDirection() {
		return organizationalDirection;
	}
	public void setOrganizationalDirection(String organizationalDirection) {
		this.organizationalDirection = organizationalDirection;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

		
}
