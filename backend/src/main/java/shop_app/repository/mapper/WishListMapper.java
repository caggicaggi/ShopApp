package shop_app.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import shop_app.dto.WishListDTO;

public class WishListMapper implements RowMapper<WishListDTO>{

	@Override
	public WishListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		WishListDTO wishListProduct =new WishListDTO();
		
		wishListProduct.setIdWishList(rs.getInt("idWishList"));
		wishListProduct.setIdProduct(rs.getInt("idProduct"));
		wishListProduct.setIdUtente(rs.getInt("idUtente"));
		
		return wishListProduct;
	}

}