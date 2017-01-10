package domain.Repositories;

import java.util.List;

import domain.User;

public interface IUserRepository extends IRepository<User> {

	public List<User> withLogin(String login);
	
	public List<User> withLoginAndPassword(String login, String password);

	void setupPermission(User user);
	
}
