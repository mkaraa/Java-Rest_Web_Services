package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.UserRest;
import modelResponse.UpdateUserDetailsRequest;
import modelResponse.UserDetailsRequestModel;

@CrossOrigin
@RestController
@RequestMapping("/users")  //http://localhost:8080/users
public class UserController {

	Map<String, UserRest> users;
	
	@GetMapping
	public String getUsers( @RequestParam(value = "page", defaultValue = "1", required = false) int page,
							@RequestParam(value = "limit", defaultValue = "20",required = false) int limit,
							@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort ) {
		//It means that :
		//http://localhost:8080/users?page=1&limit=50   -> RequestParam  
		//required = false -> it makes optional 
		return "Current page is :"+page+"\n you specified the limit is : "+limit+"\n You sorted as a "+sort;
	}
	
	@GetMapping(path = "/{userId}", 
			produces = 
		{
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
		})	//produces = MediaType....XML or JSON means that -> M-this function only response XML or JSON  

	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

		if(users.containsKey(userId)) {
			return new ResponseEntity<UserRest>(users.get(userId),HttpStatus.OK);  
		}else {
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@PostMapping(
			consumes =  
		{
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
		},
		produces = 
		{
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
		})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		
		if(users == null) users = new HashMap<String, UserRest>();
		users.put(userId, returnValue);
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);	
	}
	
	@PutMapping(
			path="/{userId}",
			consumes =  
		{
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
		},
		produces = 
		{
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
		})
			
	
	
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequest userDetails) {
		
		UserRest storedUserDetails =  users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		
		users.put(userId, storedUserDetails);
		
		return storedUserDetails;
	}
	
	@DeleteMapping(path = "/{id}") 
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		users.remove(id);
		return ResponseEntity.noContent().build();
	}
}