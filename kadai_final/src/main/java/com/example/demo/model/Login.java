package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
///記入するとGetter、Setter不要
@Data
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@Table(name = "loginuser")
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//	@NotBlank(message = "{NotNull.required-field-name}")
    private String name;
    @Max(150)
	@Min(0)
    private int age;
    @Email
//    @NotBlank(message = "{NotNull.required-field-email}")
    private String email;
//    @NotBlank(message = "{NotNull.required-field-password}")
	@Max(150)
	@Min(0)
    private String password;
	@CreatedDate
	private LocalDate start;
	@LastModifiedDate
	private LocalDate end;	
	
	
}