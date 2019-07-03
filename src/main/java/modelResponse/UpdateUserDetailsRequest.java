package modelResponse;

import javax.validation.constraints.NotNull;

public class UpdateUserDetailsRequest {

	@NotNull(message = "First name cannot be null")
	private String firstName;
	
	@NotNull(message = "Last name cannot be null")
	private String lastName;

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
	
	 
}
