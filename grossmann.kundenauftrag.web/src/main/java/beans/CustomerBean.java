package beans;

import database.CustomerRepository;
import model.Customer;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@ManagedBean(name = "customerBean", eager = true)
public class CustomerBean {

    @Inject
    CustomerRepository customerRepository;

    private List<Customer> customer;

    public List<Customer> getCustomer() {
        List<Customer> customer = customerRepository.getAll();
        return customer;
    }
}
