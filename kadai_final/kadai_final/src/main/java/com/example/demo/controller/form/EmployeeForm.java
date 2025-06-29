package com.example.demo.controller.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForm {

    @NotBlank(message = "社員名は必須です")
    private String name;

    @NotNull(message = "年齢は必須です")
    @Min(value = 0, message = "年齢は0以上で入力してください")
    @Max(value = 150, message = "年齢は150以下で入力してください")
    private Integer age;
    
    @NotBlank(message = "メールアドレスは必須です")
    @jakarta.validation.constraints.Email(message = "正しいメールアドレスを入力してください")
    private String email;

    @NotBlank(message = "パスワードは必須です")
    @Size(min = 8, max = 30, message = "パスワードは8〜30文字で入力してください")
    private String password;

    @NotBlank(message = "パスワード確認は必須です")
    private String confirmPassword;
}
