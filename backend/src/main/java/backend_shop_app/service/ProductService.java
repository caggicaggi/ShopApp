package backend_shop_app.service;

import java.util.List;

import backend_shop_app.dto.ProductDTO;

public interface ProductService {
	
	/**
	 *  Get list of all products in db
	 *
	 * @return  the list of products 
	 */
	public List<ProductDTO> getListOfProduct() throws Exception;
}
