package userservice;

import model.UserRest;
import modelResponse.UserDetailsRequestModel;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
}
