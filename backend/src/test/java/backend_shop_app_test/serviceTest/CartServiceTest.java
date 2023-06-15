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

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.request.CartRequestDTO;
import backend_shop_app.repository.CartRespository;
import backend_shop_app.service.CartServiceImpl;

public class CartServiceTest {
	
	@Mock
    private CartRespository cartRepository;
	
	@InjectMocks
	CartServiceImpl cartService;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ErrorHandler()).build();
        // Define the behavior of the mock object
        //when(cartService.addProductInCart(any())).thenReturn(1);
        //when(cartService.removeProductInCart(any())).thenReturn(1);
		List<CartDTO> cartDTOList = new ArrayList();
		CartDTO cartDTO = new CartDTO();
        when(cartRepository.findAllByIdutente(anyInt())).thenReturn(cartDTOList);    
        when(cartRepository.findAllByIdproductAndIdutente(anyInt(),anyInt())).thenReturn(cartDTO);
	}
	
	@Test
	public void testgetListOfIdCart_Ok() {
		try {
			cartService.getListOfIdCart(1);
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testaddProductInCart_Ok() {
		try {
			List<CartRequestDTO> cartDTOList = new ArrayList();
			
			CartRequestDTO cartDTO1 = new CartRequestDTO();
			cartDTO1.setIdProduct(1);
			cartDTO1.setIdUtente(1);
			cartDTO1.setQuantity(1);

			CartRequestDTO cartDTO2 = new CartRequestDTO();
			cartDTO2.setIdProduct(2);
			cartDTO2.setIdUtente(1);
			cartDTO2.setQuantity(1);
			
			cartDTOList.add(cartDTO1);
			cartDTOList.add(cartDTO2);
			
			cartService.addProductInCart(cartDTOList);
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testaddProductInCart_Error() {
		try {
			
	        when(cartRepository.findAllByIdproductAndIdutente(anyInt(),anyInt())).thenReturn(null);    

			List<CartRequestDTO> cartDTOList = new ArrayList();
			
			CartRequestDTO cartDTO1 = new CartRequestDTO();
			cartDTO1.setIdProduct(1);
			cartDTO1.setIdUtente(1);
			cartDTO1.setQuantity(1);

			CartRequestDTO cartDTO2 = new CartRequestDTO();
			cartDTO2.setIdProduct(2);
			cartDTO2.setIdUtente(1);
			cartDTO2.setQuantity(1);
			
			cartDTOList.add(cartDTO1);
			cartDTOList.add(cartDTO2);
			
			cartService.addProductInCart(cartDTOList);
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testremoveProductInCart_Ok() {
		try {
			List<CartRequestDTO> cartDTOList = new ArrayList();
			
			CartRequestDTO cartDTO1 = new CartRequestDTO();
			cartDTO1.setIdProduct(1);
			cartDTO1.setIdUtente(1);
			cartDTO1.setQuantity(1);

			CartRequestDTO cartDTO2 = new CartRequestDTO();
			cartDTO2.setIdProduct(2);
			cartDTO2.setIdUtente(1);
			cartDTO2.setQuantity(1);
			
			cartDTOList.add(cartDTO1);
			cartDTOList.add(cartDTO2);
			
			cartService.removeProductInCart(cartDTOList);
		} catch (Exception e) {
		}
	}
	@Test
	public void testremoveAllProductInCart_Ok() {
		try {
			cartService.removeAllProductInCart(2);
		} catch (Exception e) {
		}
	}

}
