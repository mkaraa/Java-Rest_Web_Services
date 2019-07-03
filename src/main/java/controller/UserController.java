package controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.UserRest;

@CrossOrigin
@RestController
@RequestMapping("/users")  //http://localhost:8080/users
public class UserController {

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

	public UserRest getUser(@PathVariable String userId) {
		UserRest returnValue = new UserRest();
		returnValue.setEmail("mete@mete.com");
		returnValue.setFirstName("Mete");
		returnValue.setLastName("KARA");
		
		return returnValue;
	}
	
	@PostMapping
	public String createUser() {
		return "create User" ; 
	}
	
	@PutMapping
	public String updateUser() {
		return "update User";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete User" ;
	}
}
