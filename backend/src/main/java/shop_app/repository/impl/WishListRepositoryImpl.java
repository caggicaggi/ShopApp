package shop_app.repository.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import shop_app.dto.WishListDTO;
import shop_app.repository.WishListRepository;
import shop_app.repository.mapper.WishListMapper;
import shop_app.utils.GenericDao;

@Repository
public class WishListRepositoryImpl extends GenericDao implements WishListRepository{
	
	public WishListRepositoryImpl(DataSource dataSource) {
	    setDataSource(dataSource);
	}
		
	private static final WishListMapper wishListMapper = new WishListMapper();

	private static final String IDWISHLIST="idWishList";
	private static final String IDPRODUCT="idProduct";
	private static final String IDUTENTE="idUtente";

	private final String QUERY_GET_LIST_OF_WISHLIST = "SELECT idProduct FROM wishList WHERE idUtente = :idUtente ;";  
	
	private final String QUERY_ADD_PRODUCT_IN_WISHLIST = 
			"INSERT INTO wishList (idUtente,idProduct) values (:idUtente, :idProduct);";  

	private final String QUERY_REMOVE_PRODUCT_IN_WISHLIST = 
			"DELETE FROM wishList WHERE idProduct = :idProduct and idUtente= :idUtente;";  

	
	/*private MapSqlParameterSource getMapSqlParameterSourceWishListDTO(WishListDTO wishListDTO) {

		MapSqlParameterSource args = new MapSqlParameterSource();
		
		args.addValue(IDWISHLIST, wishListDTO.getIdWishList());
		args.addValue(IDPRODUCT, wishListDTO.getIdProduct());
		args.addValue(IDUTENTE, wishListDTO.getIdUtente());

		return args;

	}*/

	@Override
	public List<Integer> getListOfWishListProduct(int idUtente) throws Exception {
		System.out.println("START METODO GETLISTOFWISHLISTPRODUCT");
		MapSqlParameterSource args = new MapSqlParameterSource();
		List<WishListDTO> listOfWishListProduct = new ArrayList<>();
		List<Integer> listOfIdfWishListProduct = new ArrayList<>();
		try {
			args.addValue(IDUTENTE, idUtente);
			listOfWishListProduct = getNamedParameterJdbcTemplate().query(QUERY_GET_LIST_OF_WISHLIST, args,wishListMapper );
			for (int i = 0; i < listOfWishListProduct.size(); i++) {
				listOfIdfWishListProduct.add(listOfWishListProduct.get(i).getIdProduct());
			}
		} catch (DataAccessException e) {
			StringWriter exc = new StringWriter();
			PrintWriter prw = new PrintWriter(exc);
			e.printStackTrace(prw);
			System.out.println("Errore nella chiamata del proxy "+ exc.toString());
			System.out.println("ERRORE CHIAMATA GETLISTOFWISHLISTPRODUCT --- " + e);
		}
		System.out.println("END METODO GETLISTOFWISHLISTPRODUCT");
		return listOfIdfWishListProduct;
	}


	@Override
	public int removeProductInWishList(WishListDTO wishListDTO) throws Exception {
		System.out.println("START METODO REMOVEPRODUCTINWISHLIST");
		MapSqlParameterSource args = new MapSqlParameterSource();
		int checkInsert = 0;
		try {
			args.addValue(IDUTENTE, wishListDTO.getIdUtente());
			args.addValue(IDPRODUCT, wishListDTO.getIdProduct());
			
			checkInsert = getNamedParameterJdbcTemplate().update(QUERY_REMOVE_PRODUCT_IN_WISHLIST, args);
		} catch (DataAccessException e) {
			System.out.println("ERRORE CHIAMATA REMOVEPRODUCTINWISHLIST --- " + e);
		}
		System.out.println("END METODO REMOVEPRODUCTINWISHLIST");
		return checkInsert;
	}


	@Override
	public int addPorductInWishList(WishListDTO wishListDTO) throws Exception {
		System.out.println("START METODO ADDPRODUCTINWISHLIST");
		MapSqlParameterSource args = new MapSqlParameterSource();
		int checkInsert = 0;
		try {
			args.addValue(IDUTENTE, wishListDTO.getIdUtente());
			args.addValue(IDPRODUCT, wishListDTO.getIdProduct());
			
			checkInsert = getNamedParameterJdbcTemplate().update(QUERY_ADD_PRODUCT_IN_WISHLIST, args);
		} catch (DataAccessException e) {
			System.out.println("ERRORE CHIAMATA ADDPRODUCTINWISHLIST --- " + e);
		}
		System.out.println("END METODO ADDPRODUCTINWISHLIST");
		return checkInsert;
	}

}
