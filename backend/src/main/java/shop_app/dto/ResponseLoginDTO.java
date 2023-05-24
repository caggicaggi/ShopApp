package shop_app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLoginDTO {
	
	private String result;
	private String idUtente;
	private List<ProductDTO> listOfProduct;
	private List<Integer> wishListDTO;
	private List<Integer> cartDTO;
}
