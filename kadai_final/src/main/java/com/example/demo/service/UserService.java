package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserRequest;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
	
	@Autowired
	  private UserRepository userRepository;
	  @Autowired
	  private UserMapper userMapper;
	
	public User findById(Long id) {
	    return userRepository.findById(id).get();
	  }
	public void update(UserRequest userRequest) {
		  //データベースに保存を行う為Entityを使用
		  User user = findById(userRequest.getId());
		  user.setName(userRequest.getName());
		  user.setEmail(userRequest.getEmail());
		  user.setAge(userRequest.getAge());
		  user.setPassword(userRequest.getPassword());

		  //データベースに保存
		  userRepository.save(user);
	  }
	public void delete(Long id) {
		  User user = findById(id);
		  userRepository.delete(user);
	  }

}
