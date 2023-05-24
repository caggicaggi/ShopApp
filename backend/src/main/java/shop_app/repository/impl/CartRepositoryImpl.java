package shop_app.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import shop_app.dto.CartDTO;
import shop_app.repository.CartRepository;
import shop_app.repository.mapper.CartMapper;
import shop_app.utils.GenericDao;

@Repository
public class CartRepositoryImpl extends GenericDao implements CartRepository{
	
	public CartRepositoryImpl(DataSource dataSource) {
	    setDataSource(dataSource);
	}
		
	private static final CartMapper cartMapper = new CartMapper();

	private static final String IDCART="idCart";
	private static final String IDPRODUCT="idProduct";
	private static final String IDUTENTE="idUtente";
	private static final String QUANTITY="quantity";


	private final String QUERY_GET_LIST_OF_WISHLIST = "SELECT * FROM cart WHERE idUtente = :idUtente ;";  

	
	/*private MapSqlParameterSource getMapSqlParameterSourceCartDTO(CartDTO cartDTO) {

		MapSqlParameterSource args = new MapSqlParameterSource();
		
		args.addValue(IDCART, cartDTO.getIdCart());
		args.addValue(IDPRODUCT, cartDTO.getIdProduct());
		args.addValue(IDUTENTE, cartDTO.getIdUtente());
		args.addValue(QUANTITY, cartDTO.getQuantity());

		
		return args;

	}*/

	@Override
	public List<CartDTO> getListCartProduct(int idUtente) {
		System.out.println("START METODO GETLISTCARTPRODUCT");
		MapSqlParameterSource args = new MapSqlParameterSource();
		List<CartDTO> listOfCartProduct = new ArrayList<>();
		try {
			args.addValue(IDUTENTE, idUtente);
			listOfCartProduct = getNamedParameterJdbcTemplate().query(QUERY_GET_LIST_OF_WISHLIST, args,cartMapper );
		} catch (DataAccessException e) {
			System.out.println("ERRORE CHIAMATA GETLISTCARTPRODUCT --- " + e);
		}
		System.out.println("END METODO GETLISTCARTPRODUCT");
		return listOfCartProduct;
	}


}

