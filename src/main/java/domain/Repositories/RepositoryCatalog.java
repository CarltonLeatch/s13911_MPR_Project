package domain.Repositories;

import java.sql.Connection;
import java.sql.SQLException;

import mappers.PrivilegeMapper;
import mappers.RoleMapper;
import mappers.UserMapper;
import domain.uow.IUnitOfWork;
import domain.uow.UnitOfWork;

public class RepositoryCatalog implements IRepositoryCatalog {
	
	IUserRepository usersRepo;
	IUnitOfWork uow;
	Connection connection;
	IRoleRepository rolesRepo;
	IPrivilegeRepository priviRepo;
	
	public RepositoryCatalog(Connection connection, IUnitOfWork uow) throws SQLException {
		this.connection = connection;
		this.uow = uow;
		usersRepo = new UserRepository(connection, new UserMapper(), uow);
		rolesRepo = new RoleRepository(connection,new RoleMapper(), uow);
		priviRepo = new PrivilegeRepository(connection, new PrivilegeMapper(), uow);
	}
	
	
	public IUserRepository users() {
		// TODO Auto-generated method stub
		return usersRepo;
	}
	
	public IRoleRepository roles(){
		
		return rolesRepo;
	}

	public IPrivilegeRepository privileges() {
		// TODO Auto-generated method stub
		return priviRepo;
	}
	
	
	
	public void saveAndClose() throws SQLException {
		// TODO Auto-generated method stub
		
		connection.close();
		connection = null;
	}


	public void save() throws SQLException {
		// TODO Auto-generated method stub
		uow.saveChanges();
	}


	

}
