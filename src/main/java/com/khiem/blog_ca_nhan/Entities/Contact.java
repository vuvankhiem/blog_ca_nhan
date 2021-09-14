package com.khiem.blog_ca_nhan.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    @NotEmpty(message = "Tiêu đề không được bỏ trống")
    @Size(max = 100,message = "Tiêu đề tối thiểu 1 kí tự, tối đa 100 kí tự")
    private String contactTitle;

    @NotEmpty(message = "Nội dung tin nhắn không được bỏ trống")
    @Size(max = 1500,message = "Nội dung tin nhắn tối đa 1500 kí tự")
    private String contactSubject;

    private boolean contactStatus=true;

    @NotEmpty(message = "Email không được bỏ trống")
    @Email(message = "Email không đúng định dạng")
    private String contactEmail;

    @Pattern(regexp = "^0(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", message = "Số điện thoại không hợp lệ")
    private String contactPhone;

    @NotEmpty(message = "Họ tên không được bỏ trống")
    @Size(max = 50,message = "Họ tên tối đa 50 kí tự")
    private String contactName;

}
