package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dto.LoginRequest;
import entity.User;

import java.util.Optional;

	@RestController
	@CrossOrigin
	@RequestMapping("/auth")
	public class AuthController<UserRepository> {

	    @Autowired
	    private UserRepository repo;

	    @PostMapping("/login")
	    public String login(@RequestBody LoginRequest request) {

	    	 Optional<User> user = repo.findByEmail(request.getEmail());

	        if(user.isPresent()) {
	            if(user.get().getPassword().equals(request.getPassword())) {
	                return "Login Success";
	            }
	        }
	        return "Invalid Email or Password";
	    }
	}


