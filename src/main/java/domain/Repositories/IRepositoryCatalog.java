package domain.Repositories;

import java.sql.SQLException;

public interface IRepositoryCatalog {
	
	public IUserRepository users();
	public IRoleRepository roles();
	public IPrivilegeRepository privileges();
	
	public void saveAndClose() throws SQLException;
	
	public void save() throws SQLException;
	

}
