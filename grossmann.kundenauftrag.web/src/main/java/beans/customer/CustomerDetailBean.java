package beans.customer;

import database.CustomerRepository;
import model.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;

@ManagedBean(eager = true)
@RequestScoped
public class CustomerDetailBean implements Serializable {

    private ResourceBundle msgs = ResourceBundle.getBundle("internationalization.language", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    @EJB
    private
    CustomerRepository customerRepository;

    @PostConstruct
    public void init() {

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.customer = customerRepository.getById(this.id);
    }

    private Customer customer;

    public Customer getCustomer() {
        return this.customer;
    }

    public void delete() {
        try {
            customerRepository.delete(this.customer);
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                    FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/protected/customer.xhtml"
            );
        } catch(Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs.getString("delete_error"), ex.getMessage()) );
        }
    }
}
