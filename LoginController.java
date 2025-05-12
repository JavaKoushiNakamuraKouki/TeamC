package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;

@RequestMapping("login")
@Controller
public class LoginController {
	
	private final LoginRepository repository;
       
    public LoginController(LoginRepository repository) {
        this.repository = repository;
    }
    
    @Autowired
    private LoginService loginService;
    
//　ビューの表示
    @GetMapping("/")
    public String showLoginForm(Login login,Model model) {
        model.addAttribute("login",new Login());
        return "login/Login";
    }
//　ログインボタン押下後の画面遷移
    @PostMapping("/main")
//20250220修正前
    public String login(@RequestParam String email, @RequestParam String password, Model model,Login login) {
    	
        String name = loginService.validateUser(email, password);        
        if (name.isEmpty() == false) {
            model.addAttribute("name", name);
            model.addAttribute("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            return "login/Main";
        } else{
        	// ルートパス("/")に、リダイレクト
        	model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
            return "login/Login";
        }
    }
    
 

}