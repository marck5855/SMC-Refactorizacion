package mx.com.tp.smc.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TOPERATOR_ROL")
@NamedQuery(name="ToperatorRolEntity.findAll", query="SELECT tor FROM ToperatorRolEntity tor")
public class ToperatorRolEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name= "USERNAME")    
	private String userName;
	
	@Column(name="IDROL")  
	private BigDecimal idRol;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getIdRol() {
		return idRol;
	}

	public void setIdRol(BigDecimal idRol) {
		this.idRol = idRol;
	}
	
}
