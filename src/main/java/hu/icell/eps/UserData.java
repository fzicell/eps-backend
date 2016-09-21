package hu.icell.eps;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserData {

	@NotEmpty
	@Length(max = 12)
	private String username;
	@NotEmpty
	@Length(max = 12)
    private String password;

    public UserData() {
    }   

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
