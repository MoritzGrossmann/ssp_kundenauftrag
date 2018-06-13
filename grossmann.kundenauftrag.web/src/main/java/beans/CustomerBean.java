package beans;

import database.CustomerRepository;
import model.Customer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "customerBean", eager = true)
public class CustomerBean {

    @EJB
    private
    CustomerRepository customerRepository;

    private List<Customer> customer;

    public List<Customer> getCustomer() {
        List<Customer> customer = customerRepository.getAll();
        return customer;
    }
}
