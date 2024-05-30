package com.testeStefanini.StefaniniSpring.Entities;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testeStefanini.StefaniniSpring.Entities.Enum.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    private Integer orderStatus;

    //@JsonIgnore
    @ManyToOne//Anotation que serve para mostrar que a variavel cliente é uma chave de fora e alem disso mostrar que a relação é de varios pedidos para 1
    @JoinColumn(name = "client_id")//criar uma coluna com o nome desiguinado
    private User client;

    @OneToMany(mappedBy = "id.order")// pois no OrderItem quem tem acesso aos pedidos é o id
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)//classe independente vc está mapeando pegando a variavel order do outro lado
    private Payment payment;                                //cascade all vc está mapeando o codigo para ter o mesmo id

    public Order(){
    }

    public Order(Long id, Instant moment,OrderStatus orderStatus,User client){
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }


    public Long getId() {
        return id;
    }
    public Instant getMoment() {
        return moment;
    }
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public User getClient() {
        return client;
    }

    public Set<OrderItem> getItems() {
        return items;
    }
    public Payment getPayment() {
        return payment;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setMoment(Instant moment) {
        this.moment = moment;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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
        Order other = (Order) obj;
        if (id == null) {
            if(other.getId() != null)
                return false;
        }else if(!id.equals(other.getId())){
            return false;
        }
        return true;
    }
}
