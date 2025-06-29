package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.example.demo.model.User;

@Mapper
public interface UserMapper {
	@Insert("INSERT INTO t_user(age,email,name)VALUES(#{age},#{email},#{name})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(User user);
}
