package mx.com.tp.smc.entity;

public class CatCarpetas {
	
	private int idCarpeta;
	private String nombre;
	private String path;
	private int nivel;
	private String organizacion;
	
		
	public int getIdCarpeta() {
		return idCarpeta;
	}
	public void setIdCarpeta(int idCarpeta) {
		this.idCarpeta = idCarpeta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public String getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}
	
	public CatCarpetas(String path, int nivel) {
		this.path = path;
		this.nivel = nivel;
	}
	
	public CatCarpetas() {
	}
	
	public CatCarpetas(int idCarpeta, String nombre, String path, int nivel, String organizacion) {
		this.idCarpeta = idCarpeta;
		this.nombre = nombre;
		this.path = path;
		this.nivel = nivel;
		this.organizacion = organizacion;
	}

}