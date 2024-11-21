package ironcycle.api.model.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
	
	boolean existsByCpf(String cpf);

}
