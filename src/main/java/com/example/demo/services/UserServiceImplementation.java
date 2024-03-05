package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepository repository;
	public String addUser(Users user) {
		
		repository.save(user);
		return "User is created and saved";
	}
	
	public boolean emailExists(String email) {
		if(repository.findByEmail(email)==null) {
			return false;
		}
		else {
			return true;
		}
	}

	
	public boolean validateUser(String email, String password) {
		Users user=repository.findByEmail(email);
		String db_password=user.getPassword();
		
			if(password.equals(db_password)) {
				return true;
			}
			else {
				return false;
			}
		
		
		
	}

	@Override
	public String getRole(String email) {
		return repository.findByEmail(email).getRole();
	}

	@Override
	public Users getUser(String email) {
		
		return repository.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		repository.save(user);
		
	}
	
	
}
