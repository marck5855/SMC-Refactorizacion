package mx.com.tp.smc.response;

public class Region {

	private Integer id;
	private String sym;
	
	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Region(Integer id, String sym) {
		super();
		this.id = id;
		this.sym = sym;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
