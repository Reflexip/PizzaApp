package pl.pizza.entity;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.pizza.services.PizzaManager;

@ManagedBean
@SessionScoped
public class Menu {
    
    private List<Pizza> pizzas;
    private List<Pizza> pizzasKinds;

    public Menu(){
        this.pizzas = PizzaManager.getAllPizzas();
        this.pizzasKinds = PizzaManager.getPizzasKinds();
    }
    
    public List<Pizza> getPizzasByName(String name){
        List<Pizza> pizzasByName = new ArrayList();
        for(Pizza p : this.pizzas){
            if(p.getName().equalsIgnoreCase(name))
                pizzasByName.add(p);
        }
        return pizzasByName;
    }
    
    public List<Pizza> getPizzas() {        
        return pizzas;
    }

    public void setPizzas(List<Pizza> Pizzas) {
        this.pizzas = Pizzas;
    }

    public List<Pizza> getPizzasKinds() {
        return pizzasKinds;
    }

    public void setPizzasKinds(List<Pizza> pizzasKinds) {
        this.pizzasKinds = pizzasKinds;
    }

    

   
    
    
}
