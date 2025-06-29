package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.model.Login;
//データベースにアクセス
@EnableJpaAuditing
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByNameAndPassword(String name,String password);
}
