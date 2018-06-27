package beans.order;

import database.CustomerRepository;
import model.Customer;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class CustomerService {

    @EJB
    private CustomerRepository customerRepository;

    public List<Customer> getCustomer() {
        return customerRepository.getAll();
    }
}
