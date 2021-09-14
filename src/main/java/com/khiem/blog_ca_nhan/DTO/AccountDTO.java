package com.khiem.blog_ca_nhan.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private int accountId;

    @NotEmpty(message = "Username không được bỏ trống")
    @Pattern(regexp = "^[a-zA-Z0-9]*$",message = "Nhập username không hợp lệ")
    @Size(min = 3,max = 45,message = "Username tối thiểu 3 kí tự, tối đa 45 kí tự")
    private String username;

    @NotEmpty(message = "Password không được bỏ trống")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",message = "Mật khẩu tối thiểu tám ký tự, ít nhất một chữ hoa, một chữ thường và một chữ số")
    private String password;

    @NotEmpty(message = "RePassword không được bỏ trống")
    private String re_password;

    @NotEmpty(message = "Email không được bỏ trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    private String avatar;
    private boolean active;
    private String role;

    @NotEmpty(message = "Họ tên không được bỏ trống")
    @Size(min = 3,max = 30,message ="Họ tên tối thiểu 3 kí tự và tối đa 30 kí tự" )
    @Pattern(regexp = "^[a-zA-Z \\u0080-\\u9fff]*$",message = "Nhập họ tên không hợp lệ")
    private String fullName;

    private String auth_provider;
}
