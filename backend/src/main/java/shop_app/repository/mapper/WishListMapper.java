package shop_app.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import shop_app.dto.WishListDTO;

public class WishListMapper implements RowMapper<WishListDTO>{

	@Override
	public WishListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		WishListDTO wishListProduct =new WishListDTO();
		
		wishListProduct.setIdProduct(rs.getInt("idProduct"));
		
		return wishListProduct;
	}

}