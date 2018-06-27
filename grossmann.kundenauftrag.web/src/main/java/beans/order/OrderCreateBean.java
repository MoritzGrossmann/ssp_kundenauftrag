package beans.order;

import database.CustomerRepository;
import database.OrderRepository;
import model.Customer;
import model.Order;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean
@RequestScoped
public class OrderCreateBean implements Serializable {

    ResourceBundle msgs = ResourceBundle.getBundle("internationalization.language", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    @EJB
    private OrderRepository orderRepository;

    @EJB
    private CustomerRepository customerRepository;

    @ManagedProperty("#{customerService}")
    private CustomerService customerService;

    @PostConstruct
    public void init() {
        this.customers = customerService.getCustomer();
    }

    public void submit() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.order.setDateTime(new java.sql.Date(this.dateTime.getTime()));
            if (order.getId() > 0) {
                orderRepository.update(order);
            } else {
                customer.addOrder(this.order);
                customerRepository.update(customer);
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msgs.getString("save_successful"), "Order saved successful") );
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs.getString("save_error"),  e.getMessage()));
        }
    }

    private Date dateTime = new Date();

    private int orderId;

    private int customerId;

    private Order order = new Order();

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private List<Customer> customers;

    public List<Customer> getCustomers() {
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
        this.order = orderRepository.getById(orderId);
        this.dateTime = new Date(this.order.getDateTime().getTime());
        this.customer = order.getCustomer();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        customer = customerRepository.getById(customerId);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    // TODO getter und setter für service
}
