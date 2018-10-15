
package mx.com.tp.smc.response;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.json.simple.JSONArray;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseLink {

	protected boolean success;
	protected Period timeRes;
	protected String error;
	protected String mssg;
	protected int total;
	protected String name;
	protected JSONArray array;
	protected Link link;

	public ResponseLink(boolean success, Period timeRes, String mssg, String error, int total, String name,
			JSONArray array) {
		this.success = success;
		this.timeRes = timeRes;
		this.mssg = mssg;
		this.error = error;
		this.total = total;
		this.name = name;
		this.array = array;
	}

	public ResponseLink(boolean success, Period timeRes, String mssg, String error, int total, Link link) {
		this.success = success;
		this.timeRes = timeRes;
		this.mssg = mssg;
		this.error = error;
		this.total = total;
		this.link = link;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean getSuccess() {
		return success;
	}

	public String getTimeRes() {
		PeriodFormatter format = new PeriodFormatterBuilder().appendSeconds().appendSuffix(" s", " s")
				.appendSeparator(", ").appendMillis().appendSuffix(" ms", " ms").toFormatter();
		return timeRes.toString(format);
	}

	public void setTimeRes(Period timeRes) {
		this.timeRes = timeRes;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public String getMssg() {
		return mssg;
	}

	public void setMssg(String mssg) {
		this.mssg = mssg;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDatos(JSONArray array) {
		this.array = array;
	}

	public JSONArray getDatos() {
		return array;
	}

	@Override
	public String toString() {
		return "ResponseLink [success=" + success + ", timeRes=" + timeRes + ", error=" + error + ", mssg=" + mssg
				+ ", total=" + total + ", name=" + name + ", array=" + array + ", link=" + link + "]";
	}
}
