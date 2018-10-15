package mx.com.tp.smc.entity;

import java.sql.Blob;
import java.sql.Date;

public class CatArchivos {

	private Integer idArchivo;
	private Integer idRol;
	private String  username;
	private String  archivonombre;
	private String  archivomes;
	private Integer archivoAnio;
	private Date archivofechacarga;
	private Date archivofechaactualizacion;
	private Blob archivoArchivo;
	private String organizacion;
	private int idCarpeta; 
	private String nombre; 
	private String path; 
	private int nivel; 
	private String organizacionCarp;
	
	

	public CatArchivos() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CatArchivos(Integer idArchivo, Integer idRol, String username,
			String archivonombre, String archivomes, Integer archivoAnio,
			Date archivofechacarga, Date archivofechaactualizacion,
			Blob archivoArchivo, String organizacion) {
		super();
		this.idArchivo = idArchivo;
		this.idRol = idRol;
		this.username = username;
		this.archivonombre = archivonombre;
		this.archivomes = archivomes;
		this.archivoAnio = archivoAnio;
		this.archivofechacarga = archivofechacarga;
		this.archivofechaactualizacion = archivofechaactualizacion;
		this.archivoArchivo = archivoArchivo;
		this.organizacion = organizacion;
	}

	public CatArchivos(Integer idArchivo, Integer idRol, String username,
			String archivonombre, String archivomes, Integer archivoAnio,
			Date archivofechacarga, Date archivofechaactualizacion,
			Blob archivoArchivo, String organizacion, 
			int idCarpeta, String nombre, String path, int nivel, String organizacionCarp) {
		super();
		this.idArchivo = idArchivo;
		this.idRol = idRol;
		this.username = username;
		this.archivonombre = archivonombre;
		this.archivomes = archivomes;
		this.archivoAnio = archivoAnio;
		this.archivofechacarga = archivofechacarga;
		this.archivofechaactualizacion = archivofechaactualizacion;
		this.archivoArchivo = archivoArchivo;
		this.organizacion = organizacion;
		this.idCarpeta = idCarpeta;
		this.nombre = nombre;
		this.path=path;
		this.nivel=nivel;
		this.organizacionCarp=organizacionCarp;
	}
	
	public Integer getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Integer idArchivo) {
		this.idArchivo = idArchivo;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getArchivonombre() {
		return archivonombre;
	}

	public void setArchivonombre(String archivonombre) {
		this.archivonombre = archivonombre;
	}

	public String getArchivomes() {
		return archivomes;
	}

	public void setArchivomes(String archivomes) {
		this.archivomes = archivomes;
	}

	public Integer getArchivoAnio() {
		return archivoAnio;
	}

	public void setArchivoAnio(Integer archivoAnio) {
		this.archivoAnio = archivoAnio;
	}

	public Date getArchivofechacarga() {
		return archivofechacarga;
	}

	public void setArchivofechacarga(Date archivofechacarga) {
		this.archivofechacarga = archivofechacarga;
	}

	public Date getArchivofechaactualizacion() {
		return archivofechaactualizacion;
	}

	public void setArchivofechaactualizacion(Date archivofechaactualizacion) {
		this.archivofechaactualizacion = archivofechaactualizacion;
	}

	public Blob getArchivoArchivo() {
		return archivoArchivo;
	}

	public void setArchivoArchivo(Blob archivoArchivo) {
		this.archivoArchivo = archivoArchivo;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatArchivos [idArchivo=");
		builder.append(idArchivo);
		builder.append(", idRol=");
		builder.append(idRol);
		builder.append(", username=");
		builder.append(username);
		builder.append(", archivonombre=");
		builder.append(archivonombre);
		builder.append(", archivomes=");
		builder.append(archivomes);
		builder.append(", archivoAnio=");
		builder.append(archivoAnio);
		builder.append(", archivofechacarga=");
		builder.append(archivofechacarga);
		builder.append(", archivofechaactualizacion=");
		builder.append(archivofechaactualizacion);
		builder.append(", archivoArchivo=");
		builder.append(archivoArchivo);
		builder.append(", organizacion=");
		builder.append(organizacion);
		builder.append("]");
		return builder.toString();
	}
	

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

	public String getOrganizacionCarp() {
		return organizacionCarp;
	}

	public void setOrganizacionCarp(String organizacionCarp) {
		this.organizacionCarp = organizacionCarp;
	}
	
}
