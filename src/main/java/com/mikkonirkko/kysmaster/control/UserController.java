package com.mikkonirkko.kysmaster.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mikkonirkko.kysmaster.model.SignupForm;
import com.mikkonirkko.kysmaster.model.User;
import com.mikkonirkko.kysmaster.repository.UserRepository;

import java.util.List;

import javax.validation.Valid;


@Controller
public class UserController {
	@Autowired
    private UserRepository repository; 
	
    @RequestMapping(value = "signup") //Return the signup page if there are no user accounts, otherwise the front page will load.
    public String addStudent(Model model){
    	List<User> users= (List<User>) repository.findAll();
		if(users.size()==0) {
			model.addAttribute("signupform", new SignupForm());
	        return "signup";
		}else {
        return "redirect:/";
		}
    }	
    

    @RequestMapping(value = "saveuser", method = RequestMethod.POST) //Save the new user with validation
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("ADMIN");
		    	if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		repository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
    
}
