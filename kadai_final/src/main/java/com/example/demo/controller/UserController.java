package com.example.demo.controller;


import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.form.EmployeeForm;
import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;

@EnableJpaAuditing
@RequestMapping("login") 
@Controller
public class UserController {

    private final LoginRepository repository;

    public UserController(LoginRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLoginForm(Login login, Model model) {
        model.addAttribute("login", new Login());
        return "login/Login";
    }

    @PostMapping("/main")
    public String login(@RequestParam String name,@RequestParam String password,
                        HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        if (!name.isEmpty()) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(1800);
            session.setAttribute("name", name);
            return "login/Main";        } else {
            request.setAttribute("error", "ユーザID または パスワードに 誤りがあります。");
            return "login/Login";
        }
    }

    
 //情報登録
 // 入力フォーム表示
    @GetMapping("/main/register")
    public String showForm(Model model) {
        model.addAttribute("employeeForm", new EmployeeForm());
        return "user/Register";
    }


// 社員登録処理
    @PostMapping("/main/register/input")
    public String registerEmployee(@ModelAttribute("employeeForm") @Valid EmployeeForm employeeForm,
                                   BindingResult result,
                                   Model model) {

        // パスワードと確認用パスワードの一致確認
        if (!employeeForm.getPassword().equals(employeeForm.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "パスワードが一致しません");
        }

        // 入力エラーがある場合は再表示
        if (result.hasErrors()) {
            return "/main/register/";
        }

        // 登録処理実行
        loginService.createEmployee(employeeForm);

        return "/main/register/";
    }

    // 「戻る」ボタンの処理
    @PostMapping("user/Register/back")
    public String backToPreviousPage() {
        return "redirect:/main/register"; // 登録画面に戻る
    }

//    @GetMapping("/main/register")
//    public String register(Model model) {
//        model.addAttribute("user", new Login());
//        return "user/Register";
//    }
//
//    @PostMapping("/main/register/add")
//    public String addUser(@Validated @ModelAttribute("user") Login login,
//                          BindingResult result,Model model) {
//    	model.addAttribute("users", repository.findAll());
//        if (result.hasErrors()) {
//            return "login/Main";
//        }
//
//        repository.save(login);
//        return "redirect:/login/Main"; 
//    }
    
    
//情報検索
    @GetMapping("/main/search")
    public String search(Model model) {
        model.addAttribute("user", new Login());
        return "user/Search";
    }
    
    @PostMapping("/main/search/selectByConditions")
    public String selecyByConditions(Model m,
        @RequestParam(value = "id", required = false) Long id,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "ageF", required = false) String ageF,
        @RequestParam(value = "ageT", required = false) String ageT,
        @RequestParam(value = "startF", required = false) String startF,
        @RequestParam(value = "startT", required = false) String startT,
        @RequestParam(value = "endF", required = false) String endF,
        @RequestParam(value = "endT", required = false) String endT) {

        Integer minAge = (ageF != null && !ageF.isEmpty()) ? Integer.valueOf(ageF) : null;
        Integer maxAge = (ageT != null && !ageT.isEmpty()) ? Integer.valueOf(ageT) : null;

        LocalDate minStart = (startF != null && !startF.isEmpty()) ? LocalDate.parse(startF) : null;
        LocalDate maxStart = (startT != null && !startT.isEmpty()) ? LocalDate.parse(startT) : null;
        LocalDate minEnd = (endF != null && !endF.isEmpty()) ? LocalDate.parse(endF) : null;
        LocalDate maxEnd = (endT != null && !endT.isEmpty()) ? LocalDate.parse(endT) : null;

        List<Login> members = loginService.selectByConditions(id, name, minAge, maxAge, minStart, maxStart, minEnd, maxEnd);
        m.addAttribute("member", members);
        return "user/Search";
    }
    
    @GetMapping("/main")
    public String Main(Model model) {
        model.addAttribute("user", new Login());
        return "login/Main";
    }
    
//情報削除    
    // 削除画面表示
    @PostMapping("/main/delete")
    public String deleteForm(@RequestParam(value = "ids", required = false) List<Long> ids, Model model) {
        if (ids == null || ids.isEmpty()) {
            model.addAttribute("msg", "削除対象が選択されていません");
            return "user/Search";
        }

        List<Login> deleteUsers = loginService.findByIds(ids);
        model.addAttribute("deleteUsers", deleteUsers);
        return "user/Delete";
    }

    // 1件削除
    @PostMapping("/main/delete/execute")
    public String delete(Model m, @RequestParam("id") String id) {
        long numId = Long.parseLong(id);
        loginService.delete(numId);
        m.addAttribute("msg", "削除が正常に完了しました");
        return "user/DeleteComplete";
    }

    // 複数削除用
    @PostMapping("/main/delete/executeMultiple")
    public String deleteMultiple(@RequestParam("ids") List<Long> ids, Model model) {
        loginService.deleteByIds(ids);
        model.addAttribute("msg", "複数の削除が正常に完了しました");
        return "user/DeleteComplete";
    }


//情報更新
    @GetMapping("/main/updateForm")
    public String update(Model model) {
        model.addAttribute("user", new Login());
        return "user/UpdateForm";
    }
    
    @PostMapping("/main/updateForm/update")
    public String showUpdateForm(@RequestParam("id") Long id, Model model) {
        Login user = loginService.findById(id);
        if (user == null) {
            model.addAttribute("msg", "指定されたIDのユーザーが見つかりません");
            return "user/UpdateForm";
        }
        model.addAttribute("user", user);
        return "user/Update"; // 入力フォーム画面へ
    }
    
    @PostMapping("/main/updateForm/update/uComp")
    public String updateUser(@ModelAttribute("user") Login login, Model model) {
        loginService.update(login);
        model.addAttribute("msg", "更新が正常に完了しました");
        return "user/UpdateComplete";
    }
    
//ログアウト処理
}
