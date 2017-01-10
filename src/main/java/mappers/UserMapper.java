package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.*;

public class UserMapper implements IMapResultSetToEntity<User> {
	
	public User map(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setLogin(rs.getString("Login"));
		u.setPassword(rs.getString("Password"));
		return u;
	}
	

}
