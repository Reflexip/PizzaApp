package pl.pizza.services;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import pl.pizza.entity.users.Privileges;
import pl.pizza.entity.users.User;

@ManagedBean
@SessionScoped
public class SignInManager {
    
    
    private User user;
    
    private List<User> users;
    
    
    public SignInManager() {
        this.user = null;
    }
    
    public boolean checkUserExist(User user){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzaBasePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();    

        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        this.users = query.getResultList();

        entityManager.close();
        entityManagerFactory.close(); 
            
        for(User u : users){
            if(u.getLogin().equals(user.getLogin())){
                if(u.getPassword().equals(user.getPassword())){
                    this.user = u;                
                    return true;
                }
            }           
        }
        return false;
    }
    
    public String signIn(User user){
        if(this.checkUserExist(user)){
            if(user.getPrivileges() == Privileges.RETAILER)
                return "retailer/retailerMainPage.xhtml";
            else if(user.getPrivileges() == Privileges.MANAGER)
                return "manager/managerMainPage.xhtml";
        }
        else{
            FacesContext context = FacesContext.getCurrentInstance();         
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Sorry!",  "User doesn't exist.") );
            return "/managment.xhtml";
        }
        return "/managment.xhtml";
    }
    
    public String logout(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(true);
        session.invalidate();
        return "/management";    
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
    }

    
    
    
}
