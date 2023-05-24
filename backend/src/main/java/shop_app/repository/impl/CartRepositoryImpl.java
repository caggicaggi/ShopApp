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

	private static final String IDPRODUCT="idProduct";
	private static final String IDUTENTE="idUtente";
	private static final String QUANTITY="quantity";


	private final String QUERY_GET_LIST_OF_CARTLIST = "SELECT idProduct FROM cart WHERE idUtente = :idUtente ;";  
	
	private final String QUERY_ADD_PRODUCT_IN_CART = 
			"INSERT INTO cart (idProduct,idUtente,quantity) values ( :idProduct, :idUtente, :quantity);";  

	private final String QUERY_REMOVE_PRODUCT_IN_CART = 
			"DELETE FROM cart WHERE idProduct = :idProduct and idUtente= :idUtente;";  
	
	/*private MapSqlParameterSource getMapSqlParameterSourceCartDTO(CartDTO cartDTO) {

		MapSqlParameterSource args = new MapSqlParameterSource();
		
		args.addValue(IDCART, cartDTO.getIdCart());
		args.addValue(IDPRODUCT, cartDTO.getIdProduct());
		args.addValue(IDUTENTE, cartDTO.getIdUtente());
		args.addValue(QUANTITY, cartDTO.getQuantity());

		
		return args;

	}*/

	@Override
	public List<Integer> getListCartProduct(int idUtente) {
		System.out.println("START METODO GETLISTCARTPRODUCT");
		MapSqlParameterSource args = new MapSqlParameterSource();
		List<CartDTO> listOfCartProduct = new ArrayList<>();
		List<Integer> listOfIdCartProduct = new ArrayList<>();
		try {
			args.addValue(IDUTENTE, idUtente);
			listOfCartProduct = getNamedParameterJdbcTemplate().query(QUERY_GET_LIST_OF_CARTLIST, args,cartMapper );
			for (int i = 0; i < listOfCartProduct.size(); i++) {
				listOfIdCartProduct.add(listOfCartProduct.get(i).getIdProduct());
			}
		} catch (DataAccessException e) {
			System.out.println("ERRORE CHIAMATA GETLISTCARTPRODUCT --- " + e);
		}
		System.out.println("END METODO GETLISTCARTPRODUCT");
		return listOfIdCartProduct;
	}


	@Override
	public int removeProductInCart(List<CartDTO> cartDTO) throws Exception {
		System.out.println("START METODO REMOVEPRODUCTINCART");
		MapSqlParameterSource args = new MapSqlParameterSource();
		int checkInsert = 0;
		try {
			for (int i = 0; i < cartDTO.size(); i++) {
				args.addValue(IDUTENTE, cartDTO.get(i).getIdUtente());
				args.addValue(IDPRODUCT, cartDTO.get(i).getIdProduct());
				args.addValue(QUANTITY, cartDTO.get(i).getQuantity());
				checkInsert = getNamedParameterJdbcTemplate().update(QUERY_REMOVE_PRODUCT_IN_CART, args);
			}
		} catch (DataAccessException e) {
			System.out.println("ERRORE CHIAMATA REMOVEPRODUCTINCART --- " + e);
		}
		System.out.println("END METODO REMOVEPRODUCTINCART");
		return checkInsert;
	}


	@Override
	public int addPorductInCart(List<CartDTO> cartDTO) throws Exception {
		System.out.println("START METODO ADDPRODUCTINCART");
		MapSqlParameterSource args = new MapSqlParameterSource();
		int checkInsert = 0;
		try {
			for (int i = 0; i < cartDTO.size(); i++) {
				args.addValue(IDUTENTE, cartDTO.get(i).getIdUtente());
				args.addValue(IDPRODUCT, cartDTO.get(i).getIdProduct());
				args.addValue(QUANTITY, cartDTO.get(i).getQuantity());
				checkInsert = getNamedParameterJdbcTemplate().update(QUERY_ADD_PRODUCT_IN_CART, args);
			}
		} catch (DataAccessException e) {
			System.out.println("ERRORE CHIAMATA ADDPRODUCTINCART --- " + e);
		}
		System.out.println("END METODO ADDPRODUCTINCART");
		return checkInsert;
	}


}

