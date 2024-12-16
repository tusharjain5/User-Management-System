package com.userman.usermanagementsystem.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.userman.usermanagementsystem.model.UserDtls;
import com.userman.usermanagementsystem.repo.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordencode;
	
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("pageTitle", "Welcome to Teacher Panel");
		return "teacher/home";
	}
	
	
	
	@ModelAttribute
	private void adminDetails(Model m, Principal p) {
		String email=p.getName();
		
		UserDtls admin=userRepo.findByEmail(email);
	    m.addAttribute("admin",admin);
		
	}
	
	@GetMapping("/changespass")
	public String loadchangepassword(HttpSession session, Model model) {
		model.addAttribute("pageTitle", "Forgot Password");
	    Object msg = session.getAttribute("msg");
	    if (msg != null) {
	        model.addAttribute("msg", msg);
	        session.removeAttribute("msg"); // Remove it after setting it
	    }
		return "teacher/password";
	}
	
	
	@PostMapping("/updatesPassword")
	public String changepassword(Principal p, @RequestParam("oldPass") String oldPass,
			@RequestParam("newPass") String newPass, HttpSession session
			)
	{
		   String email=p.getName();
		   
		   UserDtls loginuser = userRepo.findByEmail(email);
		   
		   boolean f=passwordencode.matches(oldPass, loginuser.getPassword());
		   
	
		   if(f) {
		   	loginuser.setPassword(passwordencode.encode(newPass));
		   UserDtls updatePassworduser=userRepo.save(loginuser);
		   
		   if(updatePassworduser!=null) {
			
			   System.out.println("Password Change Success");
			   session.setAttribute("msg", "Update Password Sucessfully");
		   }
		   else {
			   System.out.println("Something Error in Wrong");
			   session.setAttribute("msg", "Something Wrong on Server"); 
		   }
		   
		   }
		   else {
			   System.out.println("Old Password is Incorrect");
			   session.setAttribute("msg", "Old Password is Incorrect");
		   }
	
	
		   	
		return "redirect:/teacher/changespass";
	}
	
	
} 
