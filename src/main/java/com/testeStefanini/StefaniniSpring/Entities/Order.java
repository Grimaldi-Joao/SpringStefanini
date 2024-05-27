package com.testeStefanini.StefaniniSpring.Entities;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;


public class Order implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private Instant moment;

    private User client;

    public Order(){
    }

    public Order(Long id, Instant moment,User client){
        this.id = id;
        this.moment = moment;
        this.client = client;
    }

    public User getClient() {
        return client;
    }
    public Long getId() {
        return id;
    }
    public Instant getMoment() {
        return moment;
    }
    public void setClient(User client) {
        this.client = client;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}
