package mx.com.tp.smc.request;

//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Regiones {

	
	private String id;
	
	@Size(min=1, max=4000)
	private String sym;
	
	public Regiones() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Regiones(String id, String sym) {
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
		builder.append("Regiones [id=");
		builder.append(id);
		builder.append(", sym=");
		builder.append(sym);
		builder.append("]");
		return builder.toString();
	}
	
	
}
