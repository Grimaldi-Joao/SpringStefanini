package com.testeStefanini.StefaniniSpring.DTO;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testeStefanini.StefaniniSpring.Entities.Order;
import com.testeStefanini.StefaniniSpring.Entities.OrderItem;
import com.testeStefanini.StefaniniSpring.Entities.Payment;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testeStefanini.StefaniniSpring.Entities.Enum.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_order")
public class OrderDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    @OneToMany(mappedBy = "id.order")// pois no OrderItem quem tem acesso aos pedidos é o id
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)//classe independente vc está mapeando pegando a variavel order do outro lado
    private Payment payment;                                //cascade all vc está mapeando o codigo para ter o mesmo id

    public OrderDTO(){
    }

    public OrderDTO(Long id, Instant moment,OrderStatus orderStatus){
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
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
        OrderDTO other = (OrderDTO) obj;
        if (id == null) {
            if(other.getId() != null)
                return false;
        }else if(!id.equals(other.getId())){
            return false;
        }
        return true;
    }
}
