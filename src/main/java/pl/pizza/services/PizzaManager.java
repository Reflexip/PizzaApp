package pl.pizza.services;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import pl.pizza.entity.Pizza;

@ManagedBean
public class PizzaManager {
    
    public static void addPizzaToDB(Pizza pizza){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();   
        
        entityManager.getTransaction().begin();
        entityManager.persist(pizza);
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();    
    }
    
    public static Pizza loadSinglePizza(long id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();    
        
        Pizza pizza = entityManager.find(Pizza.class, id);
        
        entityManager.close();
        entityManagerFactory.close();          
        return pizza;  
    
    }
    
    public static List<Pizza> getAllPizzas(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();    
        
        TypedQuery<Pizza> query = entityManager.createQuery("select p from Pizza p", Pizza.class);
        List<Pizza> pizzas = query.getResultList();
        
        entityManager.close();
        entityManagerFactory.close();          
        return pizzas;
    }
    
    public static void changePizzaPrice(long id, double price){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager(); 
        
        entityManager.getTransaction().begin();        
        Pizza pizza = entityManager.find(Pizza.class, id);     
        pizza.setPrice(price);        
        entityManager.persist(pizza);
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();          
    }
    
    public static void removePizzaFromDB(long id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();  
        
        entityManager.getTransaction().begin();  
            Pizza pizza = entityManager.find(Pizza.class, id);
            entityManager.remove(pizza);                          
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close(); 
    }
    public static List<Pizza> getPizzasKinds(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();    
        
        TypedQuery<Pizza> query = entityManager.createQuery("select p from Pizza p GROUP BY p.name ORDER BY p.id", Pizza.class);
        List<Pizza> pizzas = query.getResultList();
        
        entityManager.close();
        entityManagerFactory.close();          
        return pizzas;    
    }
    
    public static List<Pizza> getPizzasByName(String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();    
        
        TypedQuery<Pizza> query = entityManager.createQuery("select p from Pizza p where p.name = '" + name + "' ", Pizza.class);
        List<Pizza> pizzas = query.getResultList();
        
        entityManager.close();
        entityManagerFactory.close();          
        return pizzas;
    
    }
    
}
