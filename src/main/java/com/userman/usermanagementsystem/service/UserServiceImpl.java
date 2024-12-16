package com.userman.usermanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.userman.usermanagementsystem.model.UserDtls;
import com.userman.usermanagementsystem.repo.UserRepository;

import jakarta.mail.internet.MimeMessage;
import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
    @Autowired
	private JavaMailSender mailSender;

	@Override
	public UserDtls createUser(UserDtls user, String url) {
		// TODO Auto-generated method stub
		
	   user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		
		
		user.setEnabled(false);
		RandomString rn=new RandomString();	
		user.setVerificationcode(rn.make(64));
		
		
		
		
		UserDtls us=userRepo.save(user);
		
		sendVerificationMail(user,url); 
		
	   
		return us;
		
		
		
	}
	
	
	
	

	@Override
	public boolean checkEmail(String email) {
		
		return userRepo.existsByEmail(email);
	}

	
	public void sendVerificationMail(UserDtls user, String url) {
	
		 		String from="kaminat8319@gmail.com";
		 		String to=user.getEmail();
		 		String subject="Account Verification";
		 		String content="Dear [[name]],<br>"
		 				+"Please Click the Link below to verify your registration:<br>"
		 				+"<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
		 				+"Thank you:<br>"
		 				+"Tushar";
		 				
		 		
		 		try {
		 			
		 			MimeMessage message=mailSender.createMimeMessage();
		 			MimeMessageHelper helper=new MimeMessageHelper(message);
		 			
		 			helper.setFrom(from,"Tushar");
		 			helper.setTo(to);
		 			helper.setSubject(subject);
		 			
		 			content=content.replace("[[name]]",user.getUsername());
		 			
		 			
		 			String siteUrl = url + "/verify?verificationcode=" + user.getVerificationcode();

		 			
		 			content = content.replace("[[URL]]", siteUrl);

		 			
		 			helper.setText(content,true);
		 			
		 			
		 			mailSender.send(message);
		 			
		 			
		 		}
		 		catch(Exception e) {
		 			e.printStackTrace();
		 		}
		
	}





	@Override
	public boolean verifyAccount(String verificationcode) {
	    System.out.println("Verification code received: " + verificationcode);
	    UserDtls user = userRepo.findByVerificationcode(verificationcode);
	    
	    if (user != null) {
	        System.out.println("User found: " + user.getEmail());
	        user.setEnabled(true);
	        user.setVerificationcode(null);
	        userRepo.save(user);
	        return true;
	    } else {
	        System.out.println("No user found for verification code: " + verificationcode);
	    }
	    return false;
	}

	
}
