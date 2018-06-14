package beans;

import database.CustomerRepository;
import model.Customer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "customerCreateBean")
public class CustomerCreateBean {

    @EJB
    CustomerRepository customerRepository;

    private Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void submit() {
        customerRepository.insert(this.customer);
    }
}
