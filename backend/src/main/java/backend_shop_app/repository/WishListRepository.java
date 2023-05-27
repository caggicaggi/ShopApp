package backend_shop_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend_shop_app.dto.WishListDTO;

public interface WishListRepository extends JpaRepository<WishListDTO,Integer>{
	
	public List<WishListDTO> findIdproductByIdutente(int idutente);
}
