package beans.customer;

import database.CustomerRepository;
import model.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class CustomerBean {

    @EJB
    private
    CustomerRepository customerRepository;

    @PostConstruct
    public void init() {
        this.customer = customerRepository.getAll();
    }

    private List<Customer> customer;

    public List<Customer> getCustomer() {
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
