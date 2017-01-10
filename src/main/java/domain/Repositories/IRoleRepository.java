package domain.Repositories;

import java.util.List;

import domain.Role;
import domain.User;

public interface IRoleRepository extends IRepository<Role> {

	public List<Role> withName(String name);
	
	public List<User> withLogin(String login);

}
