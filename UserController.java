package controller;

	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.User;
import repository.repository;

	@RestController
	@CrossOrigin
	@RequestMapping("/users")
	public class UserController {

	    @Autowired
	    private repository repo;

	    @PostMapping("path")
	    public User addUser (@RequestBody User user) {
	        return repo.save(user);
	    }

	   @GetMapping
	      public List<User> getAllUsers() {
	        return repo.findAll();
	    }

	    @PutMapping("/{id}")
	    public User updateUser(@PathVariable Long id, @RequestBody User user) {
	        user.setId(id);
	        return repo.save(user);
	    }
	    @DeleteMapping
	    public void deleteUser(@PathVariable Long id) {
	        repo.deleteById(id);
	    }
	}



