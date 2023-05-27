package backend_shop_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend_shop_app.dto.CartDTO;

public interface CartRespository extends JpaRepository<CartDTO,Integer>{
	
	public List<CartDTO> findAllByIdutente(int idutente);
}
