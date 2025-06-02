package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.Data;

@Entity
@Data
@Table(name = "loginuser")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//	@NotBlank(message = "{NotNull.required-field-name}")
    private String name;
    @Max(150)
	@Min(0)
    private Integer age;
    @Email
//    @NotBlank(message = "{NotNull.required-field-email}")
    private String email;
//    @NotBlank(message = "{NotNull.required-field-password}")
	@Max(150)
	@Min(0)
    private String password;
	private LocalDate start;
	private LocalDate end;
	

}
