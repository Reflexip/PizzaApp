package pl.pizza.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ingradient {
    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    
    @ManyToMany(mappedBy = "ingradients")
    private List<Pizza> pizzasThatInclude;

    public Ingradient() {
        this.pizzasThatInclude = new ArrayList();
    }

    public Ingradient(String name, Pizza pizza) {
        this.name = name;
        this.pizzasThatInclude = new ArrayList();
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
        this.name = name;
    }

    public List<Pizza> getPizzasThatInclude() {
        return pizzasThatInclude;
    }

    public void setPizzasThatInclude(List<Pizza> pizzasThatInclude) {
        this.pizzasThatInclude = pizzasThatInclude;
    }
    
    
    
}
