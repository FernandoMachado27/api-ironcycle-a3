package ironcycle.api.model.users;

public record DataDetailsUser(Long id, String name, String cpf, String birth, String phone, String email) {

	public DataDetailsUser(User user) {
		this(user.getId(), user.getName(), user.getCpf(), user.getBirth(), 
				user.getPhone(), user.getEmail());
	}

}
