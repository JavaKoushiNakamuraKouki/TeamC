package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
  //検索画面
    @GetMapping("/user/search")
    public String search() {
    	return "user/Search";
    }

  //登録画面
    @GetMapping("/user/register")
    public String register() {
    	return "user/Register";
    }
    
    
//更新画面
    @GetMapping("/user/update")
    public String update() {
    	return "user/Update";
    }

//削除画面
    @GetMapping("/user/delete")
    public String delete() {
    	return "user/Delete";
    }

//main画面
    @GetMapping("/main")
    public String main() {
    	return "login/Main";
    }
    
//ログアウト処理
    @GetMapping("/user")
    public String logout() {
    	return "login/Login";
    }
}
