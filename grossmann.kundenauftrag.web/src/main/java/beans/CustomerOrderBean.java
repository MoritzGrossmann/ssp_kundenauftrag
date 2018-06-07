package beans;

import database.CustomerRepository;
import database.Repository;
import model.Customer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "customerOrderBean")
@RequestScoped
public class CustomerOrderBean {

    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Customer customer;

    public Customer getCustomer() {
        Repository<Customer> customerRepository = new CustomerRepository();
        return customerRepository.getById(this.id);
    }
}
