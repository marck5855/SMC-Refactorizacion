
package mx.com.tp.smc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TB_MENU_ROL")
@IdClass(value = TbMenuRolPK.class)
@NamedQuery(name = "TbMenuRolEntity.findAll", query = "SELECT t FROM TbMenuRolEntity t")
public class TbMenuRolEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_MENU")
	private Long idMenu;

	@Id
	@Column(name = "IDROL")
	private Long idRol;

	@Id
	@Column(name = "ID_HOME")
	private Long idHome;

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public Long getIdHome() {
		return idHome;
	}

	public void setIdHome(Long idHome) {
		this.idHome = idHome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idHome == null) ? 0 : idHome.hashCode());
		result = prime * result + ((idMenu == null) ? 0 : idMenu.hashCode());
		result = prime * result + ((idRol == null) ? 0 : idRol.hashCode());
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
		TbMenuRolEntity other = (TbMenuRolEntity) obj;
		if (idHome == null) {
			if (other.idHome != null)
				return false;
		} else if (!idHome.equals(other.idHome))
			return false;
		if (idMenu == null) {
			if (other.idMenu != null)
				return false;
		} else if (!idMenu.equals(other.idMenu))
			return false;
		if (idRol == null) {
			if (other.idRol != null)
				return false;
		} else if (!idRol.equals(other.idRol))
			return false;
		return true;
	}

}
