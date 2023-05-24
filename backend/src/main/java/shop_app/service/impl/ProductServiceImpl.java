package shop_app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop_app.dto.ProductDTO;
import shop_app.dto.WishListDTO;
import shop_app.repository.ProductRepository;
import shop_app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<ProductDTO> getListOfProduct() throws Exception {
		return productRepository.getListOfProduct();
	}

}
