package beans.customer;

import database.CustomerRepository;
import model.Customer;
import org.primefaces.event.FlowEvent;

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

    private boolean skip;

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

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
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
