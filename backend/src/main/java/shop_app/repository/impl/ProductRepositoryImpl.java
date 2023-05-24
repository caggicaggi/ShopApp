package shop_app.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import shop_app.dto.ProductDTO;
import shop_app.dto.WishListDTO;
import shop_app.repository.ProductRepository;
import shop_app.repository.mapper.ProductMapper;
import shop_app.utils.GenericDao;


@Repository
public class ProductRepositoryImpl extends GenericDao implements ProductRepository{
	
	public ProductRepositoryImpl(DataSource dataSource) {
	    setDataSource(dataSource);
	}
		
	private static final ProductMapper productMapper = new ProductMapper();

	private static final String IDPRODUCT="idProduct";
	private static final String TITLE="title";
	private static final String DESCRIPTION="description";
	private static final String IMAGES1="images1";
	private static final String IMAGES2="images2";
	private static final String IMAGES3="images3";
	private static final String RATING="rating";
	private static final String PRICE="price";
	private static final String ISPOPULAR="isPopular";
	private static final String ISAVAILABLE="isAvailable";
	private static final String CATEGORY="category";

	private final String QUERY_GET_LIST_OF_PRODUCT = "SELECT * FROM product ;";  

	
	/*private MapSqlParameterSource getMapSqlParameterSourceProductDTO(ProductDTO productDTO) {

		MapSqlParameterSource args = new MapSqlParameterSource();
		
		args.addValue(IDPRODUCT, productDTO.getIdProduct());
		args.addValue(TITLE, productDTO.getTitle());
		args.addValue(DESCRIPTION, productDTO.getDescriprtion());
		args.addValue(IMAGES1, productDTO.getImages1());
		args.addValue(IMAGES2, productDTO.getImages2());
		args.addValue(IMAGES3, productDTO.getImages3());
		args.addValue(RATING, productDTO.getRating());
		args.addValue(PRICE, productDTO.getPrice());
		args.addValue(POPULAR, productDTO.getPopular());
		args.addValue(ISAVAILABLE, productDTO.getIsAvailable());
		args.addValue(CATEGORY, productDTO.getCategory());

		return args;

	}*/
	
	@Override
	public List<ProductDTO> getListOfProduct() throws Exception {
		System.out.println("START METODO GETLISTOFPRODUCT");
		MapSqlParameterSource args = new MapSqlParameterSource();
		List<ProductDTO> listOfProduct = new ArrayList<>();
		
		try {
			listOfProduct = getNamedParameterJdbcTemplate().query(QUERY_GET_LIST_OF_PRODUCT, args,productMapper );
		} catch (DataAccessException e) {
			System.out.println("ERRORE CHIAMATA GETLISTOFPRODUCT --- " + e);
		}
		System.out.println("END METODO GETLISTOFPRODUCT");
		return listOfProduct;
	}

	@Override
	public List<WishListDTO> getListOfWishListProduct() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
