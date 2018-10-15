package mx.com.tp.smc.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddIncident {
	@NotNull
	@Size(min = 1, max = 255)
	private String applicant;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String category;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String[] description;
	
	@Size(min = 1, max = 255)
	private String digInicial;
	
	@Size(min = 1, max = 255)
	private String tipoRed;
	
	@Size(min = 1, max = 255)
	private String region;
	
	private String insertar;
	
	
	@Size(min = 1, max = 255)
	private String org;


	public String getApplicant() {
		return applicant;
	}


	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String[] getDescription() {
		return description;
	}


	public void setDescription(String[] description) {
		this.description = description;
	}


	public String getDigInicial() {
		return digInicial;
	}


	public void setDigInicial(String digInicial) {
		this.digInicial = digInicial;
	}


	public String getTipoRed() {
		return tipoRed;
	}


	public void setTipoRed(String tipoRed) {
		this.tipoRed = tipoRed;
	}

	


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getInsertar() {
		return insertar;
	}


	public void setInsertar(String insertar) {
		this.insertar = insertar;
	}


	public String getOrg() {
		return org;
	}


	public void setOrg(String org) {
		this.org = org;
	}
	
}
