package mx.com.tp.smc.entity;

public class Categories {

	private String sym;
	private String idCategory;
	private String mssg;
	private String succes;
	private String category;
	private String subCategory;
	private String fallo;
	private String tenant;
	
	public String getSym() {
		return sym;
	}
	public void setSym(String sym) {
		this.sym = sym;
	}
	
	public String getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(String idCategory) {
		this.idCategory = idCategory;
	}
	
	
	public String getMssg() {
		return mssg;
	}
	public void setMssg(String mssg) {
		this.mssg = mssg;
	}
	
	public String getSucces() {
		return succes;
	}
	public void setSucces(String succes) {
		this.succes = succes;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	public String getFallo() {
		return fallo;
	}
	public void setFallo(String fallo) {
		this.fallo = fallo;
	}
	
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	@Override
	public String toString() {
		return "Categories [sym=" + sym + ", idCategory=" + idCategory + ", mssg=" + mssg + ", succes=" + succes
				+ ", category=" + category + ", subCategory=" + subCategory + ", fallo=" + fallo + ", tenant=" + tenant
				+ "]";
	}
}
