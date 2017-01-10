package domain;

import java.util.ArrayList;
import java.util.List;


public class User extends Entity implements IHaveId{
	
	private int id;
	private String login;
	private String password;
	
	private List<Role> roles;
	
	
	public User(){
		
		roles = new ArrayList<Role>();
		
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	
	

}
