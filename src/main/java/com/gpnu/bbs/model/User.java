package com.gpnu.bbs.model;

import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@ToString
public class User {
    private Integer id;

    @Length(min = 1, max = 20, message = "password 长度必须在 {min} - {max} 之间")
    private String nickname;

    //@Length(min = 6, max = 30, message = "email 长度必须在 {min} - {max} 之间")
    @NotBlank(message = "email 不允许为空")
    @Email
    private String email;

    @Length(min = 6, max = 20, message = "password 长度必须在 {min} - {max} 之间")
    private String password;

    private String sign;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date reg_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }
}