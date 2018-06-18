package beans.customer;

import beans.MessageBean;
import database.CustomerRepository;
import model.Customer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CustomerCreateBean {

    @EJB
    private
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
        MessageBean messageBean = MessageBean.getCurrentInstance();
        try {
            if (customer.getId() == 0) {
                customerRepository.insert(this.customer);
            } else {
                customerRepository.update(customer);
            }

            messageBean.showInfo("Success","Customer saved successful");
        } catch (Exception e) {
            messageBean.showError("Error","Error during saving of Customer");
        }
    }

}
