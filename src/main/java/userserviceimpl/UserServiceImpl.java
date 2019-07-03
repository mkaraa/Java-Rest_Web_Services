package userserviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import model.UserRest;
import modelResponse.UserDetailsRequestModel;
import userservice.UserService;

public class UserServiceImpl implements UserService{

	
	Map<String, UserRest> users;
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		
		if(users == null) users = new HashMap<String, UserRest>();
		users.put(userId, returnValue);
		
		return returnValue;
	}

}
