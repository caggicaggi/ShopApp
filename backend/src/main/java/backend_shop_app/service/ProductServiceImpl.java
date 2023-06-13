package backend_shop_app.service;

import java.util.List;

import backend_shop_app.dto.ProductDTO;

public interface ProductServiceImpl {
	
	public List<ProductDTO> getListOfProduct() throws Exception;
}
