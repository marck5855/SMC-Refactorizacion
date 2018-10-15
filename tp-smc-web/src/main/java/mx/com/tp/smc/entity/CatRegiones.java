package mx.com.tp.smc.entity;

public class CatRegiones {

	public String id;
	public String sym;
	
	public CatRegiones() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CatRegiones(String id, String sym) {
		super();
		this.id = id;
		this.sym = sym;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSym() {
		return sym;
	}

	public void setSym(String sym) {
		this.sym = sym;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatRegiones [id=");
		builder.append(id);
		builder.append(", sym=");
		builder.append(sym);
		builder.append("]");
		return builder.toString();
	}
	
	
}
