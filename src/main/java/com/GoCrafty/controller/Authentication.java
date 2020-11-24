package com.GoCrafty.controller;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.GoCrafty.entity.User;
import com.GoCrafty.service.AuthenticationService;

@Controller
@RequestMapping("home/authentication")
public class Authentication {
	
	@Autowired
	AuthenticationService  authenticationService;
	
	@RequestMapping("/login")
	public String login(Model theModel,@ModelAttribute("user") User theUser,@RequestParam("role") String role,@SessionAttribute(name="tempSession") HashMap<String,String> userSession)
	{
		
		String id=authenticationService.login(theUser,role);
		if (id==null)
		{
			int retry=Integer.parseInt(userSession.get("retry"));
			if(retry>=3)
			{
				//email block
				userSession.put("retry", "0");//reset counter
			}
			retry=retry+1;
			userSession.put("retry",String.valueOf(retry));
			theModel.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/userLogin?role="+role;
		}
		
		 if(role.equals("student"))
		{
			userSession.put("id", id);
			return "redirect:/home/student/viewProfile";
		}
		else if(role.equals("instructor"))
		{
			userSession.put("id", id);
			return "redirect:/home/instructor/viewProfile";
		}
		else {
			int retry=Integer.parseInt(userSession.get("retry"));
			if(retry>=3)
			{
				//email block
			}
			retry=retry+1;
			userSession.put("retry",String.valueOf(retry));
			theModel.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/userLogin?role="+role;
		}
	}
	
	@RequestMapping("/logOut")
	public String logOut(@SessionAttribute(name="tempSession") HashMap<String,String> userSession, HttpServletRequest request) {
	try {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
		}
	catch (Exception e) {
		
		return "redirect:/";
		}
	}

}
