package com.testeStefanini.StefaniniSpring.Entities;
import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_order")
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    //@JsonIgnore
    @ManyToOne//Anotation que serve para mostrar que a variavel cliente é uma chave de fora e alem disso mostrar que a relação é de varios pedidos para 1
    @JoinColumn(name = "client_id")//criar uma coluna com o nome desiguinado
    private User client;

    public Order(){
    }

    public Order(Long id, Instant moment,User client){
        this.id = id;
        this.moment = moment;
        this.client = client;
    }


    public Long getId() {
        return id;
    }
    public Instant getMoment() {
        return moment;
    }

    public User getClient() {
        return client;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public void setClient(User client) {
        this.client = client;
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
            if(other.getId() != null)
                return false;
        }else if(!id.equals(other.getId())){
            return false;
        }
        return true;
    }
}
