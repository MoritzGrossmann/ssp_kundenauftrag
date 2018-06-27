package beans.customer;

import database.CustomerRepository;
import model.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;

@ManagedBean
@ViewScoped
public class CustomerCreateBean implements Serializable {

    @EJB
    private
    CustomerRepository customerRepository;

    @PostConstruct
    public void init() {

    }

    ResourceBundle msgs = ResourceBundle.getBundle("internationalization.language", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    //region Form-Parameter

    private String firstname;

    private String lastname;

    private String email;

    private String street;

    private String houseNumber;

    private String postcode;

    private String city;

    private String country;

    private String telefonPrivate;

    private String telefonMobile;

    private String fax;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelefonPrivate() {
        return telefonPrivate;
    }

    public void setTelefonPrivate(String telefonPrivate) {
        this.telefonPrivate = telefonPrivate;
    }

    public String getTelefonMobile() {
        return telefonMobile;
    }

    public void setTelefonMobile(String telefonMobile) {
        this.telefonMobile = telefonMobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    //endregion

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
        if (id != 0) {
            this.customer = customerRepository.getById(id);
            setFormParameter(this.customer);
        }
    }

    private Customer customer;

    private void setFormParameter(Customer customer) {
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();
        this.email = customer.getEmail();
        this.street = customer.getStreet();
        this.houseNumber = customer.getHouseNumber();
        this.postcode = customer.getPostcode();
        this.city = customer.getCity();
        this.country = customer.getCountry();
        this.telefonPrivate = customer.getTelefonPrivate();
        this.telefonMobile = customer.getTelefonMobile();
        this.fax = customer.getFax();
    }

    private Customer getCustomerFromForm() {
        Customer customer = this.customer == null ? new Customer() : this.customer;
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customer.setEmail(email);
        customer.setStreet(street);
        customer.setHouseNumber(houseNumber);
        customer.setPostcode(postcode);
        customer.setCity(city);
        customer.setCountry(country);
        customer.setTelefonMobile(telefonMobile);
        customer.setTelefonPrivate(telefonPrivate);
        customer.setFax(fax);
        return customer;
    }

    public void save() {
        try {
            Customer customer = getCustomerFromForm();
            if (customer.getId() == 0) {
                customerRepository.insert(customer);
            } else {
                customerRepository.update(customer);
            }

            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/protected/customer-detail.xhtml?id=" + customer.getId());

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(msgs.getString("save_error"), e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
