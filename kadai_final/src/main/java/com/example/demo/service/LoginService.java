package com.example.demo.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.form.EmployeeForm;
import com.example.demo.dto.LoginRequest;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService {
    
    @Autowired
    private LoginRepository loginRepository;

    private final LoginMapper loginMapper;

    @Autowired
    public LoginService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }
    
    public Login findById(Long id) {
        return loginRepository.findById(id).orElse(null); 
    }
    //ログイン
    public Optional<Login>sarchUserByName(String name){
    	return loginRepository.findByName(name);
    }
    
    // 検索処理
    public List<Login> selectByConditions(Long id, String name, Integer minAge, Integer maxAge,
            LocalDate minStart, LocalDate maxStart,
            LocalDate minEnd, LocalDate maxEnd) {
        return loginMapper.selectByConditions(id, name, minAge, maxAge, minStart, maxStart, minEnd, maxEnd);
    }
    
    // 削除機能
    public void delete(Long id) {
        loginMapper.delete(id);
    }
    
    public List<Login> findByIds(List<Long> ids) {
        return loginMapper.findByIds(ids);
    }

    // 複数削除
    public void deleteByIds(List<Long> ids) {
        loginMapper.deleteByIds(ids);
    }

    // ユーザー名＋パスワードでユーザーを探し、存在しなければ空文字を返す
    public String validateUser(String name, String password) {
        Login user = loginRepository.findByNameAndPassword(name, password);
        if (user == null) {
            return "";
        } else {
            return user.getName();
        }
    }

    // 更新用（DTOからModelへセット）
    public void update(LoginRequest loginRequest) {
        Login login = findById(loginRequest.getId());
        if (login != null) {
            login.setName(loginRequest.getName());
            login.setEmail(loginRequest.getEmail());
            login.setPassword(loginRequest.getPassword());
            loginMapper.update(login);
        }
    }

// 社員情報の新規登録
    @Transactional
    public Login createEmployee(EmployeeForm form) {
        Login entity = new Login();

        entity.setName(form.getName());
        entity.setAge(form.getAge());
        entity.setEmail(form.getEmail());
        entity.setPassword(form.getPassword());

        // 登録日の自動設定
        entity.setStart(LocalDate.now());

        return loginRepository.save(entity);
    }

    // 更新
    public boolean update(Login login) {
        Login existing = findById(login.getId());
        if (existing != null) {
            loginMapper.update(login);
            return true;
        }
        return false;
    }
}