package beans;

import database.CustomerRepository;
import model.Customer;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "customerDetailBean", eager = true)
public class CustomerDetailBean {

    @Inject
    CustomerRepository customerRepository;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Customer customer;

    public Customer getCustomer() {
        if (this.customer == null || this.id != this.customer.getId())
            this.customer = customerRepository.getById(id);
        return this.customer;
    }

}
