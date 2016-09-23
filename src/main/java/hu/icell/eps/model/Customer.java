package hu.icell.eps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "customer")
@JsonInclude(Include.NON_NULL)
public class Customer
{

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty(value="userId")
	private Integer custId;

	@NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String username;	

    @NotNull
    private String password;

    @NotNull
    private Integer age;

    
// ------------------------
// PUBLIC METHODS
// ------------------------
    
    public Customer() {
	}    	
        
    // Getter and setter methods

    public Customer(String firstName, String LastName, String username, String password, Integer age) {
    	setFirstName(firstName);
    	setLastName(LastName);
    	setUsername(username);
    	setPassword(password);
    	setAge(age);
    	
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public Integer getCustId() {
		return custId;
	}
	
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}