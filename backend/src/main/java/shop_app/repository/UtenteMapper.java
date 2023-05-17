package shop_app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import shop_app.dto.UtenteDTO;

public class UtenteMapper implements RowMapper<UtenteDTO>{
	
		@Override
		public UtenteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UtenteDTO utenteDTO = new UtenteDTO();
			utenteDTO.setEmail(rs.getString("email"));
			utenteDTO.setPassword(rs.getString("password"));
			return utenteDTO;
		}

}
