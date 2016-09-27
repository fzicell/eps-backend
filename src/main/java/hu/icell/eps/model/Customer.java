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
	private Integer customerId;

	@NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String username;	

    @NotNull
    private String password;
    
    @NotNull
    private Integer balance;

	@NotNull
    private Integer age;

    
// ------------------------
// PUBLIC METHODS
// ------------------------
    
    public Customer() {
	}    	

    public Customer(String firstName, String lastName, String username, String password, Integer age) {
    	this.firstName=firstName;
    	this.lastName=lastName;
    	this.username=username;
    	this.password=password;
    	this.age=age;    	
	}

    // Getter and setter methods

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
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
		
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

}