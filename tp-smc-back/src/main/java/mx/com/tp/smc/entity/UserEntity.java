package mx.com.tp.smc.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
@NamedQueries({
	@NamedQuery( name= "UserEntity.findAll"
			   , query="SELECT	u "
			   		  +"FROM	UserEntity u" ), 
	@NamedQuery( name= "UserEntity.findAllFilter"
			   , query="SELECT	us "
			   		  +"FROM	UserEntity us "
			          +"WHERE	us.createuser=?" )
})
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name= "USERNAME")    
	private String userName;
	
	@Column(name="PASSWORD")  
	private String password;
	
	@Column(name="ENABLED")      
	private BigDecimal enabled;
	
	@Column(name="ROLE")        
	private String role;
	
	@Column(name="NAME")                 
	private String name;
	
	@Column(name= "ORGANIZATION")           
	private String organization;
	
	@Column(name= "CREATEUSER")
	private String createuser;
	
	@Column(name= "USERSNUM")
	private String usernum;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getEnabled() {
		return enabled;
	}

	public void setEnabled(BigDecimal enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
	
	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getUsernum() {
		return usernum;
	}

	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}
	
	
	
}
