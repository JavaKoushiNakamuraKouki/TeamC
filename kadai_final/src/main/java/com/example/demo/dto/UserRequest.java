package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.Data;

@Data
public class UserRequest {
	
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
