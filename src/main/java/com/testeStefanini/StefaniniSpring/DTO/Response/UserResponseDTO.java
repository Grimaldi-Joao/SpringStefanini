package com.testeStefanini.StefaniniSpring.DTO.Response;

import java.io.Serializable;

import com.testeStefanini.StefaniniSpring.Entities.User;


public class UserResponseDTO implements Serializable{
    private static final long serialVersionUID = 1L; //Numero do Serializable

    private Long id;
    private String name;
    private String email;
    private String phone;

    public UserResponseDTO(User objUser){
        this.id = objUser.getId();
        this.name = objUser.getName();
        this.email = objUser.getEmail();
        this.phone = objUser.getPhone();
    }

    public UserResponseDTO(){}//Por padrão

    public String getEmail() {
        return email;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
