package pl.pizza.entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
@Entity
public class CustomersOrder implements Serializable {
//FIELDS------------------------------------------------------------------------
    @Id
    @GeneratedValue
    private long id;
    
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Temporal(TemporalType.TIME)
    private Date sendTime;
    
    @ManyToMany(mappedBy = "ordersThatInclude")
    private List<Pizza> listOfPizzas = new ArrayList();  
    
    @ManyToOne
    private DeliveryAddress deliveryAddress;
    
    private double orderValue;    
    private OrderState orderState;
    
    private PaymentType paymentType;
    private int paymentRadioChoose;
    
    
   
//CONSTRUCTOR-------------------------------------------------------------------    
    public CustomersOrder() {       		
        this.orderDate = new Date();
        this.orderValue = 0.00;
        this.orderState = OrderState.CREATING;
        this.paymentType = PaymentType.CASH;
    }
    
//METHODES----------------------------------------------------------------------
    public void addPizza(Pizza pizza){
        this.listOfPizzas.add(pizza);
    }
    public void removePizza(Pizza pizza){
        this.listOfPizzas.remove(pizza);   
    }
    
    public String goAccept(){    
        return "basket.xhtml";
    }
    
//GETTERS-AND-SETTERS-----------------------------------------------------------
    
    
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    
    public List<Pizza> getListOfPizzas() {
        return listOfPizzas;
    }

    public void setListOfPizzas(List<Pizza> listOfPizzas) {
        this.listOfPizzas = listOfPizzas;
    }

    public String getOrderValue() {
        this.orderValue = 0;
        for(Pizza p : this.getListOfPizzas())
            this.orderValue += p.getPrice();
        DecimalFormat f = new DecimalFormat("0.00");
        return f.format(this.orderValue);
    }

    public void setOrderValue() {
        for(Pizza p : this.getListOfPizzas())
            this.orderValue += p.getPrice();
            
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public String setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return "orderSummary.xhtml";
    }

     public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public int getPaymentRadioChoose() {
        return paymentRadioChoose;
    }

    public void setPaymentRadioChoose(int paymentRadioChoose) {
        if(paymentRadioChoose == 1)
            this.paymentType = PaymentType.CARD;
        else this.paymentType = PaymentType.CASH;  
        this.paymentRadioChoose = paymentRadioChoose;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    
    
   
  
}
