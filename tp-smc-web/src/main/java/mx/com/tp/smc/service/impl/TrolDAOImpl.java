package mx.com.tp.smc.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import mx.com.tp.smc.entity.TrolEntity2;
import mx.com.tp.smc.service.TrolDAO;

public class TrolDAOImpl implements TrolDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public List<TrolEntity2> getRoles() {
		List<TrolEntity2> rol = new ArrayList<TrolEntity2>();
		rol = (ArrayList<TrolEntity2>)jdbcTemplate.query("SELECT * FROM TROL", new Object[] {}, new RowMapper<TrolEntity2>() {

			public TrolEntity2 mapRow(ResultSet rs, int i) throws SQLException {
				TrolEntity2 roll = new TrolEntity2();
				roll.setIdRol(rs.getBigDecimal("IDROL"));	
				roll.setRolDescription(rs.getString("ROL_DESCRIPTION"));
				roll.setNombreOrganizacion(rs.getString("NOMBRE_ORGANIZACION"));
				roll.setOrganizacion(rs.getString("ORGANIZACION"));
				roll.setRolRole(rs.getString("ROL_ROLE"));
				return roll;
			}
		});
		return rol;
	}
}