package mx.com.tp.smc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "sdm_ca_organization")
@NamedQuery(name = "CAOrganizationEntity.findAll", query = "SELECT d FROM CAOrganizationEntity d")
public class CAOrganizationEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "organization_uuid")
	private String organizationId;

	@Column(name = "org_name")
	private String orgName;

	@Column(name = "abbreviation")
	private String abbreviation;

	@Column(name = "inactive")
	private Integer inactive;

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Integer getInactive() {
		return inactive;
	}

	public void setInactive(Integer inactive) {
		this.inactive = inactive;
	}

}
