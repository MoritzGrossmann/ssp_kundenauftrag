package beans;

import database.CustomerRepository;
import model.Customer;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

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

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        customer = customerRepository.getById(this.id);
    }

    public void submit() {
        try {
            if (customer.getId() == 0) {
                customerRepository.insert(this.customer);
            } else {
                customerRepository.update(customer);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer saved successful"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while saving customer"));
        }
    }
}
