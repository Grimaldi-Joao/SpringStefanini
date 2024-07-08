package com.testeStefanini.StefaniniSpring.DTO;


import java.io.Serializable;

import org.hibernate.validator.constraints.Length;



public class UserDTOPost implements Serializable {
    private static final long serialVersionUID = 1L; //Numero do Serializable
    private String name;
    private String email;
    private String phone;
    @Length(min = 8, message = "the password must not be at least 8 characters long")
    private String password;


    public UserDTOPost(String name, String email, String phone, String password){
        super();
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public UserDTOPost(){}//Por padrão


    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }public String getPhone() {
        return phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }



}
