package mx.com.tp.smc.model;

import java.util.ArrayList;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import mx.com.tp.smc.entity.CatArchivos;
import mx.com.tp.smc.entity.CatCarpetas;
import mx.com.tp.smc.entity.File;

public class FileModel extends MainModel {

	private ArrayList<CatArchivos> archivosPdf;
	private ArrayList<File> archivo;
	private CommonsMultipartFile[] files;
	private String path;
	private int nivel;
	private ArrayList<CatCarpetas> listaCapeta;
	
	public ArrayList<File> getArchivo() {
		return archivo;
	}

	public void setArchivo(ArrayList<File> archivo) {
		this.archivo = archivo;
	}


	public CommonsMultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(CommonsMultipartFile[] files) {
		this.files = files;
	}

	public ArrayList<CatArchivos> getArchivosPdf() {
		return archivosPdf;
	}

	public void setArchivosPdf(ArrayList<CatArchivos> archivosPdf) {
		this.archivosPdf = archivosPdf;
	}

	@Override
	public String toString() {
		return "FileModel [archivo=" + archivo + "]";
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

	public ArrayList<CatCarpetas> getListaCapeta() {
		return listaCapeta;
	}

	public void setListaCapeta(ArrayList<CatCarpetas> listaCapeta) {
		this.listaCapeta = listaCapeta;
	}

}
