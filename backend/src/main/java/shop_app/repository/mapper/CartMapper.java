package shop_app.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import shop_app.dto.CartDTO;

public class CartMapper implements RowMapper<CartDTO>{

	@Override
	public CartDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CartDTO cart =new CartDTO();
		
		cart.setIdCart(rs.getInt("idCart"));
		cart.setIdProduct(rs.getInt("idProduct"));
		cart.setIdUtente(rs.getInt("idUtente"));
		cart.setQuantity(rs.getInt("quantity"));

		
		return cart;
	}

}