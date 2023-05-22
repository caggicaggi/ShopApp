package shop_app.service;

import java.util.List;

import shop_app.dto.ProductDTO;
import shop_app.dto.WishListDTO;

public interface ProductService {

    public List<ProductDTO> getListOfProduct() throws Exception;
    
}
