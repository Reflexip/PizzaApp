package pl.pizza.services;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import pl.pizza.entity.CustomersOrder;
import pl.pizza.entity.OrderState;

@ManagedBean
public class OrderManager {        
        
        public static List<CustomersOrder> getAllOrders(){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();    

            TypedQuery<CustomersOrder> query = entityManager.createQuery("select o from CustomersOrder o", CustomersOrder.class);
            List<CustomersOrder> orders = query.getResultList();

            entityManager.close();
            entityManagerFactory.close();          
            return orders;    
        }

        public static CustomersOrder getSingleOrder(long id){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();    

            CustomersOrder customersOrder = entityManager.find(CustomersOrder.class, id);

            entityManager.close();
            entityManagerFactory.close();          
            return customersOrder;  

        }
        public static void removeOrderFromDB(long id){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();  

            entityManager.getTransaction().begin();            
                CustomersOrder customersOrder = entityManager.find(CustomersOrder.class, id);
                System.out.println("Removing order id: " + customersOrder.getId());
                entityManager.remove(customersOrder);                          
            entityManager.getTransaction().commit();

            entityManager.close();
            entityManagerFactory.close(); 
        }
        
        public String addOrderToDB(CustomersOrder customersOrder){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();   
            
            customersOrder.setOrderState(OrderState.SENT);       
            customersOrder.setSendTime(new Date());
            
            entityManager.getTransaction().begin();
                entityManager.persist(customersOrder.getDeliveryAddress());  
                entityManager.persist(customersOrder);  
            entityManager.getTransaction().commit();

            entityManager.close();
            entityManagerFactory.close();  
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(false);
            session.invalidate();
            return "successPage.xhtml";
        }
        
        public static void changeOrderState(long id, OrderState orderState){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();  
            
            entityManager.getTransaction().begin();  
                CustomersOrder customersOrder = entityManager.find(CustomersOrder.class, id);
                customersOrder.setOrderState(orderState);
                entityManager.persist(customersOrder);                         
            entityManager.getTransaction().commit();   
        }    
    }
