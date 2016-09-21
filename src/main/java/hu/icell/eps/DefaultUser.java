package hu.icell.eps;

import org.hibernate.type.CustomType;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DefaultUser {

    private int userId = 33;   
	private String username = "validuser";
    @JsonIgnore
    private String password = "validpass";

    public DefaultUser() {
    }
    
    public DefaultUser(CustomType custom) {
    	
    }

    public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
