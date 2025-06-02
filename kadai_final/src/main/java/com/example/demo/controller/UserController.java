package com.example.demo.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;

@RequestMapping("login")
@Controller
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final LoginRepository repository;
       
    public UserController(LoginRepository repository) {
        this.repository = repository;
    }
    
    @Autowired
    private LoginService loginService;
    
    @Autowired 
    private UserService userService;
    
//    @Autowired
//    private HttpSession session;
   
    
 //　ビューの表示
    @GetMapping("/")
    public String showLoginForm(Login login,Model model) {
        model.addAttribute("login",new Login());
        return "login/Login";
    }
   
//　ログインボタン押下後の画面遷移
    @PostMapping("/main")
//20250220修正前
    public String login(
    		HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		if (name.isEmpty() == false) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(1800);				// セッションタイムアウト(既定値=1800)設定
			session.setAttribute("name", name);
    }
    		
   
    	
//    	String n = (String)session.getAttribute("name");
//    	String p = (String)session.getAttribute("password");
    	
               
        if (name.isEmpty() == false) {
        	HttpSession session = request.getSession();
//            model.addAttribute("name", name);
//            model.addAttribute("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            return "login/Main";
        } else{
        	// ルートパス("/")に、リダイレクト
        	request.setAttribute("error", "ユーザID または パスワードに 誤りがあります。");
            return "login/Login";
        }
    }
    
    



}
	


