package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LoginRequest;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;


//認証処理
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private final LoginMapper loginMapper;

	@Autowired
	public LoginService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }
    
	public Login findById(Long id) {
		  return loginRepository.findById(id).get();
    }
	
	public String validateUser(String email, String password) {
		Login user = loginRepository.findByEmailAndPassword(email, password); 
		  if(user== null) {
			  return "";  
		  }else {
			  return user.getName();
		  }
	    }
	
	public void update(LoginRequest loginRequest) {
		  Login login = findById(loginRequest.getId());
		  login.setName(loginRequest.getName());
		  login.setEmail(loginRequest.getEmail());
		  login.setPassword(loginRequest.getPassword());
	  }
	  public void insert(Login Login) {
		  loginMapper.insert(Login);
	  }
	  
	  public void update(Login login) {
		  loginMapper.update(login);
	  }
}