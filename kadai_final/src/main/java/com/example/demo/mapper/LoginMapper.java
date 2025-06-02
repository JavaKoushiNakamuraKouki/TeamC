package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Login;

@Mapper
public interface LoginMapper {
//入力されたemail,passwordから該当データの検索
	@Select("SELECT * FROM LoginUser WHERE name = #{name} AND password = #{password}")
	Login findByUser(String name, String password);
//新規ユーザーの追加
    @Insert("INSERT INTO t_login(name, email, password) VALUES(#{name}, #{email}, #{password})")
   @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Login login);
//登録したユーザー情報の変更
    @Update("UPDATE t_login SET name = #{name}, email = #{email}, password = #{password} WHERE id = #{id}")
    void update(Login login);
}