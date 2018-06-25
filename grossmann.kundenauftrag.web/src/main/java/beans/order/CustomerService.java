package beans.order;

import database.CustomerRepository;
import model.Customer;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class CustomerService {

    @EJB
    private CustomerRepository customerRepository;

    public List<Customer> getCustomer() {
        return customerRepository.getAll();
    }
}
