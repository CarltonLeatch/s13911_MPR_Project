package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Privilege;
import domain.Role;
import domain.User;
import domain.Repositories.RepositoryCatalog;
import domain.Repositories.UserRepository;


public class RoleMapper implements IMapResultSetToEntity<Role> {

	RepositoryCatalog catalogOf;
	
	public Role map(ResultSet rs) throws SQLException {
		Role r = new Role();
		
		List<Privilege> privileages;
		List<User> users = new ArrayList<User>();
		r.setId(rs.getInt("id"));			
		r.setUser_id(rs.getInt("user_id"));								
		r.setName(rs.getString("name"));
		return r;
	}
	
}
