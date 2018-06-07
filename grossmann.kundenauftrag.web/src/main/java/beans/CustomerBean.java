package beans;

import database.CustomerRepository;
import database.Repository;
import model.Customer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "customerBean")
@SessionScoped
public class CustomerBean {

    private List<Customer> customer;

    public List<Customer> getCustomer() {
        Repository<Customer> customerRepository = new CustomerRepository();
        return customerRepository.getAll();
    }
}
