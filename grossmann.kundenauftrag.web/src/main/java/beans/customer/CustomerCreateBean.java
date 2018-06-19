package beans.customer;

import database.CustomerRepository;
import model.Customer;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

@ManagedBean
@RequestScoped
public class CustomerCreateBean {

    @EJB
    private
    CustomerRepository customerRepository;

    ResourceBundle msgs = ResourceBundle.getBundle("internationalization.language", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    private Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getHeaderText() {
        return this.id > 0 ? msgs.getString("customer_name_singular") + " " + this.id + msgs.getString("change")
                : msgs.getString("customer_add");
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        customer = customerRepository.getById(this.id);
    }

    public void save() {
        try {
            if (customer.getId() == 0) {
                customerRepository.insert(this.customer);
            } else {
                customerRepository.update(customer);
            }

            FacesMessage msg = new FacesMessage(msgs.getString("save_successful"), "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(msgs.getString("save_error"), e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
