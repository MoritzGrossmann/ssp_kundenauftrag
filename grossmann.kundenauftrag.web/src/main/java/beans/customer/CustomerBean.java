package beans.customer;

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
        if (customer == null) {
            this.customer = customerRepository.getAll();
        }
        return this.customer;
    }

    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void search() {
        this.customer = customerRepository.getByName(searchText);
    }
}
