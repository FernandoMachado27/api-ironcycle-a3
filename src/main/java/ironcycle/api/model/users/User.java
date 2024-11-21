package ironcycle.api.model.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String cpf;
	private String birth;
	private String phone;
	private String email;
	
	public User(@Valid DataRegistrationUser userData) {
		this.name = userData.name();
		this.cpf = userData.cpf();
		this.birth = userData.birth();
		this.phone = userData.phone();
		this.email = userData.email();
	}
	
	public void updateUser(@Valid DataUpdateUser dataUpdateUser) {
		if (dataUpdateUser.name() != null) {
			this.name = dataUpdateUser.name();
		}
		
		if (dataUpdateUser.cpf() != null) {
			this.cpf = dataUpdateUser.cpf();
		}
		
		if (dataUpdateUser.birth() != null) {
			this.birth = dataUpdateUser.birth();
		}
		
		if (dataUpdateUser.phone() != null) {
			this.phone = dataUpdateUser.phone();
		}
		
		if (dataUpdateUser.email() != null) {
			this.email = dataUpdateUser.email();
		}
	
	}
}
