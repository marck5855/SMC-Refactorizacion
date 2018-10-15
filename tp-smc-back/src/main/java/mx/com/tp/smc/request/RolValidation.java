
package mx.com.tp.smc.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class RolValidation {
	@NotNull

	private BigDecimal idrole;

	public BigDecimal getIdrole() {
		return idrole;
	}

	public void setIdrole(BigDecimal idrole) {
		this.idrole = idrole;
	}

}
