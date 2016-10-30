package pl.pizza.services;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import pl.pizza.entity.Ingradient;
import pl.pizza.entity.Pizza;

@ManagedBean
public class IngradientManager {
    public static void addIngradientToDB(Ingradient ingradient){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();   
        
        entityManager.getTransaction().begin();
        entityManager.persist(ingradient);
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();    
    }
    
    public static Ingradient getSingleIngradient(long id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();    
        
        Ingradient ingradient = entityManager.find(Ingradient.class, id);
        
        entityManager.close();
        entityManagerFactory.close();          
        return ingradient;  
    
    }
    
    public static List<Ingradient> getAllIngradients(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();    
        
        TypedQuery<Ingradient> query = entityManager.createQuery("select i from Ingradient i", Ingradient.class);
        List<Ingradient> ingradients = query.getResultList();
        
        entityManager.close();
        entityManagerFactory.close();          
        return ingradients;
    }
    
    public static void changeIngradientName(long id, String newName){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager(); 
        
        entityManager.getTransaction().begin();        
        Ingradient ingradient = entityManager.find(Ingradient.class, id);     
        ingradient.setName(newName);        
        entityManager.persist(ingradient);
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();          
    }
    
    public static void removeIngradientFromDB(long id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();  
        
        entityManager.getTransaction().begin();  
            Ingradient ingradient = entityManager.find(Ingradient.class, id);
            System.out.println("Removing Ingradient : "+ ingradient.getName()  + " id: " + ingradient.getId());
            for(Pizza p : ingradient.getPizzasThatInclude()){
                p.getIngradients().remove(ingradient);
                entityManager.persist(p);
            }
            entityManager.remove(ingradient);                          
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close(); 
    }
    
    
}
