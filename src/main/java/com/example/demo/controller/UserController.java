package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Song;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserController {
	@Autowired
	UserService service;
	@Autowired
	SongService Songservice;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {
		boolean userstatus=service.emailExists(user.getEmail());
		if(userstatus==false) {
			service.addUser(user);
			return "registerSuccess";
		}
		else {
			System.out.println("user already exists!");
			return "registerFail";
		}
	}
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
		//invoking validUser in service
		if(service.validateUser(email, password)==true) {
			//checking whether the use is admin or customer
			session.setAttribute("email", email);
			if(service.getRole(email).equals("admin"))
				return "adminhome";
			else {
				return "customerhome";
			}
		}
		else {
			return "loginfail";
		}
		
	}
	
	@GetMapping("/exploreSongs")
	public String exploreSongs(HttpSession session, Model model) {  //@RequestParam String email
		String email=(String)session.getAttribute("email");
		Users user = service.getUser(email);
		List<Song> songlist=Songservice.fetchAllSongs();
		boolean userStatus= user.isPremium();
		if(userStatus==true) {
			model.addAttribute("songlist", songlist);
			return "displaysongs";
		}
		else {
			return "makePayment";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "map-login";
	}
	
	
	
}
