package shop_app.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import shop_app.dto.ProductDTO;

public class ProductMapper implements RowMapper<ProductDTO>{

	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ProductDTO product =new ProductDTO();
		
		product.setIdProduct(rs.getInt("idProduct"));
		product.setTitle(rs.getString("title"));
		product.setDescriprtion(rs.getString("description"));
		product.setImages1(rs.getString("images1"));
		product.setImages2(rs.getString("images2"));
		product.setImages3(rs.getString("images3"));
		product.setRating(rs.getInt("rating"));
		product.setPrice(rs.getInt("price"));
		product.setPopular(rs.getString("popular"));
		product.setIsAvailable(rs.getString("isAvailable"));
		product.setCategory(rs.getString("category"));

		
		return product;
	}

}