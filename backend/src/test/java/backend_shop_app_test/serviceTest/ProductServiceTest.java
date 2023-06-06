package backend_shop_app_test.serviceTest;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import backend_shop_app.dto.ProductDTO;
import backend_shop_app.repository.ProductRepository;
import backend_shop_app.service.ProductService;

public class ProductServiceTest {

	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	ProductService productService;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ErrorHandler()).build();
        // Define the behavior of the mock object
        //when(WishListService.addProductInWishList(any())).thenReturn(1);
        //when(WishListService.removeProductInWishList(any())).thenReturn(1);
		List<ProductDTO> productDTOList = new ArrayList();
        when(productRepository.findAll()).thenReturn(productDTOList);   
	}
	
	@Test
	public void testgetListOfProduct(){
		try {
			productService.getListOfProduct();
		} catch (Exception e) {
		}
	}
}
