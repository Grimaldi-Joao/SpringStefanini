package com.testeStefanini.StefaniniSpring.Entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

import jakarta.persistence.Entity;//usamos esse pois é sempre melhor a classe depender da especificação
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity //isso demonstra que essa classe é uma entidade de um banco de dado, isso pertime converter a entidade para um modelo relacional
@Table(name ="tb_user")// renomeando o nome da tabela da classe pois User é palavra reservada no banco de dados
public class User implements Serializable {
    private static final long serialVersionUID = 1L; //Numero do Serializable

    @Id//Chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)//como é uma chave numeria essa anotatio serve para demonstrar para o banco de dados que essa variavel é auto incrementavel
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    @JsonIgnore//Na hora da compilação ele ignora essa parte, ele aqui foi utilizado para parar um Loop que tinha na iteração de User com Order
                //complementando a ideia de cima, quando se faz uma associação de 1 para muitos voce tem que ussar essa anotatio, pois senão da erro de memoria já que vai está e loop


    @OneToMany(mappedBy = "client")//fazendo o outro lado da relação, mostrando que a relação de user com order é de 1 para varias
    private List<Order> orders = new ArrayList<>();

    public User(Long id,String name, String email, String phone, String password){
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public User(){}//Por padrão

    public List<Order> getOrders() {
        return orders;
    }

    public String getEmail() {
        return email;
    }
    public Long getId() {
        return id;
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
    public void setId(Long id) {
        this.id = id;
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

    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime*result+((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if(other.id != null)
                return false;
        }else if(!id.equals(other.id)){
            return false;
        }
        return true;
    }



}