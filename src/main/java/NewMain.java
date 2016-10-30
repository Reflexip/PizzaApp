
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pl.pizza.entity.CustomersOrder;
import pl.pizza.entity.DeliveryAddress;
import pl.pizza.entity.Ingradient;
import pl.pizza.entity.Pizza;
import pl.pizza.entity.Size;
import pl.pizza.services.IngradientManager;



public class NewMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();  
        
        
               
        //Create pizza
        Pizza pizza = new Pizza();
        
        pizza.setName("Jalapeno");
        pizza.setPrice(12.99);
        pizza.setSize(Size.SMALL);
    
        //Create order
        CustomersOrder order = new CustomersOrder();
        order.addPizza(pizza);
        order.setOrderValue();
        
        pizza.getOrdersThatInclude().add(order);
        
        //Create delivery details
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setSurname("Mudlaff");
        deliveryAddress.setStreet("Poniatowskiego 8");
        deliveryAddress.setPostCode("84-240");
        deliveryAddress.setCity("Reda");
        deliveryAddress.setPhone("660-043-233");
        
        order.setDeliveryAddress(deliveryAddress);
        
        //Create ingradients and add to Pizza
        Ingradient ingradient1 = new Ingradient("Gyros", pizza);
        Ingradient ingradient2 = new Ingradient("Sos pomidorowy", pizza);
        Ingradient ingradient3 = new Ingradient("Ser", pizza);
        Ingradient ingradient4 = new Ingradient("Papryczki Chilli", pizza);
        
        List<Ingradient> ingradients = new ArrayList();
        ingradients.add(ingradient1);
        ingradients.add(ingradient2);
        ingradients.add(ingradient3);
        ingradients.add(ingradient4);
        
        pizza.setIngradients(ingradients);
    
        //Connect to base and save all objects
        entityManager.getTransaction().begin();        
            entityManager.persist(ingradient1);
            entityManager.persist(ingradient2);
            entityManager.persist(ingradient3);
            entityManager.persist(ingradient4);
            entityManager.persist(deliveryAddress);
            entityManager.persist(order);
            entityManager.persist(pizza);   
        entityManager.getTransaction().commit();
        
        
//        entityManager.getTransaction().begin();
//            entityManager.remove(pizza);
//        entityManager.getTransaction().commit();
        
        
        
        entityManager.close();
        entityManagerFactory.close();
        
        IngradientManager.removeIngradientFromDB(ingradient3.getId());
        //PizzaManager.changePizzaName(1L, "Chicken");
        //PizzaManager.removePizzaFromDB(pizza.getId());
      //  OrderManager.removeOrderFromDB(order.getId());
        
        
    }
    
}
