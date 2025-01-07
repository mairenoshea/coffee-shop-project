package com.oshea.coffee_shop.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oshea.coffee_shop.models.LoginUser;
import com.oshea.coffee_shop.models.User;
import com.oshea.coffee_shop.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	

	
	public User findByEmail(String email) {
		if(userRepo.findByEmail(email).isPresent()) {
    		return userRepo.findByEmail(email).get();
    	}
    	else {
    		return null;
    	}

	}
	
	public User findById(Long id) {
		if(userRepo.findById(id).isPresent()) {
    		return userRepo.findById(id).get();
    	}
    	else {
    		return null;
    	}

	}
//	
//	
//	public List<Team> findUsersAuthoredTeams(User user) {
//		if(userRepo.findById(user.getId()).isPresent()) {
//			return (List<Team>) user.getTeamsAuthored();
//		}
//		else {
//			return null;
//		}
//	}
//	
//	
//	
//	
//	public List<Team> findUsersTeam(User user) {
//		return user.getTeamsAuthored();
//	}
//	
	
	
	 public User register(User newUser, BindingResult result) {
	    	//Is the email already taken?
	    	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
	    	if(potentialUser.isPresent()) {
	    		result.rejectValue("email", "Exists", "the email is already taken!");
	    		return null;
	    	}
	    		//	Does the entered password match the confirmation password?
	    	
	    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
	    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
	    	    return null;
	    	}
	    	if(result.hasErrors()) {
	    		return null;
	    	}
	    	else {
	    		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
	    		newUser.setPassword(hashedPassword);
	    		userRepo.save(newUser);
	    		return newUser;
	    	}
	    	
	        // TO-DO: Additional validations!
	 
	    }
	 
	 public User login(LoginUser newLoginObject, BindingResult result) {
	        // TO-DO - Reject values:
	    	// Find user in the DB by email
	    	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
	        // Reject if NOT present
	    	if(!potentialUser.isPresent()){
	    		result.rejectValue("email", "Exists", "Email is not yet registered.");
	    	}
	    
	    	User existingUser = potentialUser.get();
	    	String savedHashedPassword = existingUser.getPassword();
	    	String attemptPassword = newLoginObject.getPassword();
	    	
	    	if(!BCrypt.checkpw(attemptPassword, savedHashedPassword)) {
	    		result.rejectValue("password", "Matches", "Invalid password!");
	    	}
	    		
	    	// Return null if result has errors
	    	if (result.hasErrors()) {
	    		return null;
	    	}
	        // Otherwise, return the user object
	    	else {
	    		return existingUser;
	    	}
	    	
	    }

	 public User update(User user) {
		 userRepo.save(user);
		 return user;
	 }
}
