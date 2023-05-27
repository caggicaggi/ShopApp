package backend_shop_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend_shop_app.dto.ProductDTO;


public interface ProductRepository extends JpaRepository<ProductDTO,Integer>{
	
    List<ProductDTO> findAll();


}
