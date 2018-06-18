package beans.customer;

import database.CustomerRepository;
import model.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CustomerDetailBean {

    @EJB
    private
    CustomerRepository customerRepository;

    @PostConstruct
    public void init() {

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        Customer customer = customerRepository.getById(this.id);
        this.customer = customer;
    }

    private Customer customer;

    public Customer getCustomer() {
        return this.customer;
    }
}
