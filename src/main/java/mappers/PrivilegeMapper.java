package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Privilege;
import domain.Role;

public class PrivilegeMapper implements IMapResultSetToEntity<Privilege> {

	public Privilege map(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Privilege p = new Privilege();
		p.setId(rs.getInt("id"));
		p.setRole_id(rs.getInt("role_id"));
		p.setName(rs.getString("name"));
		return p;
	}

}
