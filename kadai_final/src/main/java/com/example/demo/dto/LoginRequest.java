package com.example.demo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class LoginRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//	(message = "{NotNull.required-field-name}")の部分はメッセージ内容を定義したファイルがありその中の値を出力する
//	場所はresourcesのvalidationMessages.proparties
	@NotBlank(message = "{NotNull.required-field-name}")
	private String name;
	@NotBlank(message = "{NotNull.required-field-email}")
	@Email
    private String email;
	@NotBlank(message = "{NotNull.required-field-password}")
	@Max(150)
	@Min(0)
    private String password;

}