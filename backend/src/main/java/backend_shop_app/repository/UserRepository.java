package backend_shop_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend_shop_app.dto.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO,Integer> {
	
    UserDTO findByEmail(String email);

    @Override
    <S extends UserDTO> S save(S userDTO);
    
    /*
     * save(S entity) 
     * findById(ID id)
     * findOne()
     * findAll()s
     */
   
}
