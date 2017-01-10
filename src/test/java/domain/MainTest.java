package domain;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import domain.Repositories.IRepositoryCatalog;
import domain.Repositories.RepositoryCatalog;
import domain.uow.IUnitOfWork;
import domain.uow.UnitOfWork;

public class MainTest {

	@Test
	public void test() throws SQLException {

    	Connection connection = DriverManager.getConnection(""
				+ "jdbc:hsqldb:hsql://localhost/workdb");
    	IUnitOfWork uow = new UnitOfWork(connection);
    	IRepositoryCatalog catalogOf = new RepositoryCatalog(connection , uow);
    	 catalogOf.users();
    	 catalogOf.roles();
    	 catalogOf.privileges();
    	
    	 Privilege p = new Privilege();
    	 p.setName("Privilege");
    	 p.setRoles(catalogOf.roles().withName("xxx"));
    	 p.setRole_id(3);
    	 
    	User u = new User();
    	u.setLogin("Rafal");
    	u.setPassword("xxx");
    	u.setRoles(catalogOf.roles().withName("Role"));
    	uow.saveChanges();
    	
    	Role r = new Role();
    	r.setName("Role2");
    	r.setUser_id(2);
    	r.setUsers(catalogOf.users().withLogin("Rafal"));
    	
    catalogOf.privileges().add(p);
    	catalogOf.roles().add(r);
    	catalogOf.users().add(u);
    	
    	catalogOf.save();
    	//System.out.println(catalogOf.users().withLogin("Rafal"));
    	
    	System.out.println("USER DB:");
    	for(User users: catalogOf.users().getAll())
    		System.out.println(users.getId() + " " + users.getLogin() + " " + users.getPassword());
    	
    	System.out.println("ROLE DB:");
    	for(Role roles: catalogOf.roles().getAll())
    		System.out.println(roles.getId() + " " + roles.getName() + " " + roles.getUser_id());
    	
    	System.out.println("PRIVILEGE DB:");
    	for(Privilege privileges: catalogOf.privileges().getAll())
    		System.out.println(privileges.getId() + " " + privileges.getName() + " " + privileges.getRole_id());
    	
    	catalogOf.saveAndClose();
    	
        System.out.println("Koniec");
    
	}

}
