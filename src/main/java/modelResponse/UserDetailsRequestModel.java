package modelResponse;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {
	
	// @NotNull and @Size annotations are for validation
	
	@NotNull(message = "First name cannot be null")
	private String firstName;
	
	@NotNull(message = "Last name cannot be null")
	private String lastName;
	
	@NotNull(message = "Email cannot be null")
	@Email
	private String email;
	
	@NotNull(message = "Password cannot be null")
	@Size(min = 8, max = 16)
	private String password;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
