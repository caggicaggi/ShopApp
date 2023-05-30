package backend_shop_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend_shop_app.dto.ProductDTO;
import backend_shop_app.repository.ProductRepository;

/*
 * Product Management Class
 */
@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	/**
	 *  Get list of all products in db
	 *
	 * @return  the list of products 
	 */
		public List<ProductDTO> getListOfProduct() throws Exception {
			return productRepository.findAll();
		}

}
