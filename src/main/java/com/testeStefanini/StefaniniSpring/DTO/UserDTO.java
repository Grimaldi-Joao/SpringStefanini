package com.testeStefanini.StefaniniSpring.DTO;

import java.io.Serializable;

import com.testeStefanini.StefaniniSpring.Entities.User;


public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L; //Numero do Serializable

    private Long id;
    private String name;
    private String email;
    private String phone;

    public UserDTO(Long id,String name, String email, String phone){
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public UserDTO(User objUser){
        this.id = objUser.getId();
        this.name = objUser.getName();
        this.email = objUser.getEmail();
        this.phone = objUser.getPhone();
    }

    public UserDTO(){}//Por padrão

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
