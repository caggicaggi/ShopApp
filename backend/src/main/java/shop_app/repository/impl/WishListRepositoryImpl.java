package shop_app.repository.impl;

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

	private final String QUERY_GET_LIST_OF_WISHLIST = "SELECT * FROM wishList WHERE idUtente = :idUtente ;";  

	
	/*private MapSqlParameterSource getMapSqlParameterSourceWishListDTO(WishListDTO wishListDTO) {

		MapSqlParameterSource args = new MapSqlParameterSource();
		
		args.addValue(IDWISHLIST, wishListDTO.getIdWishList());
		args.addValue(IDPRODUCT, wishListDTO.getIdProduct());
		args.addValue(IDUTENTE, wishListDTO.getIdUtente());

		return args;

	}*/

	@Override
	public List<WishListDTO> getListOfWishListProduct(int idUtente) throws Exception {
		System.out.println("START METODO GETLISTOFWISHLISTPRODUCT");
		MapSqlParameterSource args = new MapSqlParameterSource();
		List<WishListDTO> listOfWishListProduct = new ArrayList<>();
		try {
			args.addValue(IDUTENTE, idUtente);
			listOfWishListProduct = getNamedParameterJdbcTemplate().query(QUERY_GET_LIST_OF_WISHLIST, args,wishListMapper );
		} catch (DataAccessException e) {
			System.out.println("ERRORE CHIAMATA GETLISTOFWISHLISTPRODUCT --- " + e);
		}
		System.out.println("END METODO GETLISTOFWISHLISTPRODUCT");
		return listOfWishListProduct;
	}

}
