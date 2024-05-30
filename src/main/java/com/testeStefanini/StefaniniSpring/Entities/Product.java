package com.testeStefanini.StefaniniSpring.Entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    //@Transient essa anotation faz com que o jpa não tente rodar oq vc queira
    @ManyToMany
    @JoinTable(name = "tb_product_category", //nome da tabela de associação 
    joinColumns = @JoinColumn(name = "product_id"),//define a chave estrangeira que está recebendo
    inverseJoinColumns = @JoinColumn(name = "category_id"))// aqui vc está especificando a rela ção de muitos para muitos de Product com category, e esse invertido define a chave estrangeira do outro lado da relação
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    public Product(){}

    public Product(Long id, String name, String description, Double price, String imgUrl){

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Double getPrice() {
        return price;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    @JsonIgnore
    public Set<Order> getOrders() {// esse get é a forma de chegar no order pela classe product, criamos uma lista de OrderItens, e esse get a percorre passando por todos os elementos dela e pegando os seus respectivos order
        Set<Order> set = new HashSet<>();
        for (OrderItem x : items) {
            set.add(x.getOrder());
        }
        return set;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
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
        Product other = (Product) obj;
        if (id == null) {
            if(other.getId() != null)
                return false;
        }else if(!id.equals(other.getId())){
            return false;
        }
        return true;
    }
}
