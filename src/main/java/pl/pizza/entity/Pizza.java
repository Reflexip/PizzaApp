package pl.pizza.entity;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;


@Entity
public class Pizza{   
    
//FIELDS------------------------------------------------------------------------
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double price;   
    
    @ManyToMany
    @JoinColumn(name = "pizza_id")
    private List<CustomersOrder> ordersThatInclude;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizza_id")
    private List<Ingradient> ingradients;
    
  
    private int likes;
    private Size size;
    private String imgAddress;
    
//CONSTRUCTOR-------------------------------------------------------------------
    public Pizza() {
        this.ordersThatInclude = new ArrayList();
        this.ingradients = new ArrayList();
    }
    
//METHODES----------------------------------------------------------------------  
    
    public String getImgAddress() {
        return imgAddress;
    }

//GETTERS-AND-SETTERS-----------------------------------------------------------
    public void setImgAddress(String imgAddress) {    
        this.imgAddress = imgAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {       
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<CustomersOrder> getOrdersThatInclude() {
        return ordersThatInclude;
    }

    public void setOrdersThatInclude(List<CustomersOrder> ordersThatInclude) {
        this.ordersThatInclude = ordersThatInclude;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<Ingradient> getIngradients() {
        return ingradients;
    }

    public void setIngradients(List<Ingradient> ingradients) {
        this.ingradients = ingradients;
    }
    
    
}
