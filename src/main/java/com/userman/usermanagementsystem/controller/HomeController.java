package com.userman.usermanagementsystem.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.userman.usermanagementsystem.model.UserDtls;
import com.userman.usermanagementsystem.repo.UserRepository;
import com.userman.usermanagementsystem.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userservice;
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
	
		if(p!=null) {
		String email=p.getName();
		
		UserDtls user=userRepo.findByEmail(email);
	    m.addAttribute("user",user);
		
	}
	
	}
	
	
	

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("pageTitle", "🙍 User Management 🏻‍♂️");
		return "index";
	}

	@GetMapping("/register")
	public String showRegisterPage(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Sign Up");
	    Object msg = session.getAttribute("msg");
	    if (msg != null) {
	        model.addAttribute("msg", msg);
	        session.removeAttribute("msg"); // Remove it after setting it
	    }
	    return "register";
	}

	@GetMapping("/signin")
	public String login(Model model) {
		model.addAttribute("pageTitle", "Login ");
		return "Login";
	}

	@PostMapping("/createUser")
	public String createuser(@ModelAttribute UserDtls user, HttpSession session, HttpServletRequest request) {
		
		System.out.println("User object received: " + user);
		  
		String url = request.getRequestURL().toString().replace(request.getRequestURI(), "") + request.getContextPath();

		
	
		
		
		boolean f=userservice.checkEmail(user.getEmail());
		
		if(f) {
			session.setAttribute("msg", "Email is Already Exist");
			System.out.println("Email is Already Exist");
		}
		else {
		
			UserDtls userDtls = userservice.createUser(user,url);
	        if (userDtls != null) {
	    		session.setAttribute("msg", "Register Successfully");
	    		System.out.println("Register Sucessfully");
	        } else {
	    		session.setAttribute("msg", "Something Wrong on Server");
	    		System.out.println("Something Wrong on Server");
	        }
	        
	        
		
		}

		  System.out.println("Session message: " + session.getAttribute("msg"));
		return "redirect:/register";
	}
	
	
	@GetMapping("/loadforgotpassword")
	public String loadforgotpassword(Model model, HttpSession session) {
		model.addAttribute("pageTitle", "Forgot Password");
	    Object msg = session.getAttribute("msg");
	    if (msg != null) {
	        model.addAttribute("msg", msg);
	        session.removeAttribute("msg"); // Remove it after setting it
	    }
		return "forgotpassword";
	}
	
	@GetMapping("/loadresetpassword/{id}")
   public String loadresetpassword(@PathVariable Integer  id,Model model) {
		 model.addAttribute("pageTitle", "Reset Password");
		 model.addAttribute("id", id);
		 
		 System.out.println(id);
		return "resetpassword";
	}

	
	@PostMapping("/forgotPassword")
	public String forgotpassword(@RequestParam String email,@RequestParam String phonenumber, HttpSession session) {
		
		UserDtls user=userRepo.findByEmailAndPhonenumber(email,phonenumber);

		
		if(user != null) {
			 System.out.println("User found: " + user.getEmail());
		        return "redirect:/loadresetpassword/" + user.getId();
			
		}
		else {
		
			session.setAttribute("msg", "Invalid Email & Mobile Number");
			System.out.println("Invalid Email & Mobile Number");
			 return "redirect:/loadforgotpassword"; 
			
		}
		
	}
	
	@PostMapping("/resetPassword")
	public String resetpassword(@RequestParam String psw,  @RequestParam String confirmPsw, @RequestParam Integer id, HttpSession session) {
		
		  if (!psw.equals(confirmPsw)) {
			  System.out.println("Password do not match !");
		        session.setAttribute("msg", "Passwords do not match!");
		        return "redirect:/loadresetpassword/" + id;
		    }
		    UserDtls user = userRepo.findById(id).get();
		    String encryptPsw = passwordEncoder.encode(psw);
		    user.setPassword(encryptPsw);
		    userRepo.save(user);

		    session.setAttribute("msg", "Password Updated Successfully");
		    return "redirect:/signin";
	}
	
	
	
	@GetMapping("/verify")
	public String verifyAccount(@Param("verificationcode") String verificationcode) {
	    System.out.println("Verification Code: " + verificationcode);
	    if (userservice.verifyAccount(verificationcode)) {
	        System.out.println("Verification successful");
	        return "verifysuc";
	    } else {
	        System.out.println("Verification failed");
	        return "failed";
	    }
	}

	
	
	

}
