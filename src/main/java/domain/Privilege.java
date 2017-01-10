package domain;

import java.util.ArrayList;
import java.util.List;

public class Privilege extends Entity implements IHaveId{
	
	private String name;
	
	private List<Role> roles;
	
	private int role_id;
	
	public Privilege(){
		
		roles = new ArrayList<Role>();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	

}
