package beans.order;

import database.CustomerRepository;
import database.OrderRepository;
import model.Customer;
import model.Order;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "orderCreateBean")
@SessionScoped
public class OrderCreateBean {

    @EJB
    private OrderRepository orderRepository;

    @EJB
    private CustomerRepository customerRepository;

    @PostConstruct
    public void init() {

    }

    public void submit() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.order.setDateTime(new java.sql.Date(this.dateTime.getTime()));
            if (order.getId() > 0) {
                orderRepository.update(order);
            } else {
                orderRepository.insert(order);
            }
            context.addMessage(null, new FacesMessage("Succcess",  "Order saved successful") );
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Error",  e.getMessage()) );
        }
    }

    private Date dateTime = new Date();

    private int orderId;

    private int customerId;

    private Order order = new Order();

    private List<Customer> customers;

    public List<Customer> getCustomers() {
        if (customers == null) {
            customers = customerRepository.getAll();
        }
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Order getOrder() {
        if (orderId > 0) {
            this.order = orderRepository.getById(orderId);
        } else {
            this.order = new Order();
            if (customerId > 0) {
                order.setCustomer(customerRepository.getById(customerId));
            }
        }
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDateTime() {
        if (dateTime == null) {
            dateTime = new Date();
        }
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
