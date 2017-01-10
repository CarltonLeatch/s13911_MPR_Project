package domain;

import java.util.*;

public class Role extends Entity implements IHaveId{
	
	private String name;
	private int user_id;
	
	private List<Privilege> privileges;
	private List<User> users;
	
	
	public Role(){
		
		privileges = new ArrayList<Privilege>();
		users = new ArrayList<User>();
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Privilege> getPrivileages() {
		return privileges;
	}
	public void setPrivileages(List<Privilege> privileages) {
		this.privileges = privileages;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	
	
}
