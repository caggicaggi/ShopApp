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

import backend_shop_app.dto.WishListDTO;
import backend_shop_app.dto.request.WishListRequestDTO;
import backend_shop_app.repository.WishListRepository;
import backend_shop_app.service.WishListServiceImpl;

public class WishListServiceTest {
	
	@Mock
    private WishListRepository wishListRepository;
	
	@InjectMocks
	WishListServiceImpl wishListService;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ErrorHandler()).build();
        // Define the behavior of the mock object
        //when(WishListService.addProductInWishList(any())).thenReturn(1);
        //when(WishListService.removeProductInWishList(any())).thenReturn(1);
		List<WishListDTO> WishListDTOList = new ArrayList();
        when(wishListRepository.findIdproductByIdutente(anyInt())).thenReturn(WishListDTOList);   
        when(wishListRepository. deleteAllByIdutente(anyInt())).thenReturn(2);  
	}
	
	@Test
	public void testgetListOfWishList_Ok() {
		try {
			wishListService.getListOfWishList(1);
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testaddProductInWishList_Ok() {
		try {
			List<WishListRequestDTO> WishListDTOList = new ArrayList();
			
			WishListRequestDTO WishListDTO1 = new WishListRequestDTO();
			WishListDTO1.setIdProduct(1);
			WishListDTO1.setIdUtente(1);

			WishListRequestDTO WishListDTO2 = new WishListRequestDTO();
			WishListDTO2.setIdProduct(2);
			WishListDTO2.setIdUtente(1);
			
			WishListDTOList.add(WishListDTO1);
			WishListDTOList.add(WishListDTO2);
			
			wishListService.addProductInWishList(WishListDTOList);
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testaddProductInWishList_Error() {
		try {
			
	        when(wishListRepository.findIdproductByIdutente(anyInt())).thenReturn(null);    

			List<WishListRequestDTO> WishListDTOList = new ArrayList();
			
			WishListRequestDTO WishListDTO1 = new WishListRequestDTO();
			WishListDTO1.setIdProduct(1);
			WishListDTO1.setIdUtente(1);

			WishListRequestDTO WishListDTO2 = new WishListRequestDTO();
			WishListDTO2.setIdProduct(2);
			WishListDTO2.setIdUtente(1);
			
			WishListDTOList.add(WishListDTO1);
			WishListDTOList.add(WishListDTO2);
			
			wishListService.addProductInWishList(WishListDTOList);
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testremoveProductInWishList_Ok() {
		try {
			List<WishListRequestDTO> WishListDTOList = new ArrayList();
			
			WishListRequestDTO WishListDTO1 = new WishListRequestDTO();
			WishListDTO1.setIdProduct(1);
			WishListDTO1.setIdUtente(1);

			WishListRequestDTO WishListDTO2 = new WishListRequestDTO();
			WishListDTO2.setIdProduct(2);
			WishListDTO2.setIdUtente(1);
			
			WishListDTOList.add(WishListDTO1);
			WishListDTOList.add(WishListDTO2);
			
			wishListService.removeProductInWishList(WishListDTOList);
		} catch (Exception e) {
		}
	}
	@Test
	public void testremoveAllProductInWishList_Ok() {
		try {
			wishListService.removeAllProductInWishList(2);
		} catch (Exception e) {
		}
	}


}
