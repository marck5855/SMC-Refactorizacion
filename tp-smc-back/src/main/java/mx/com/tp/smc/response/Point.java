package mx.com.tp.smc.response;

public class Point{
	
	private String userId;
	private String name;
	private String tenant;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	@Override
	public String toString() {
		return "Point [userId=" + userId + ", name=" + name + ", tenant=" + tenant + "]";
	}
}
