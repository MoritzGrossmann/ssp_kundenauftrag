package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Repräsemtiert eine Kunden-Entität
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "firstname")
    private String firstname = "";

    @Basic
    @Column(name = "lastname")
    private String lastname= "";

    @Basic
    @Column(name = "telefon_private")
    private String telefonPrivate= "";

    @Basic
    @Column(name = "telefon_mobile")
    private String telefonMobile= "";

    @Basic
    @Column(name = "fax")
    private String fax= "";

    @Basic
    @Column(name = "email")
    private String email= "";

    @Basic
    @Column(name = "street")
    private String street= "";

    @Basic
    @Column(name = "house_number")
    private String houseNumber= "";

    @Basic
    @Column(name = "postcode")
    private String postcode= "";

    @Basic
    @Column(name = "city")
    private String city= "";

    @Basic
    @Column(name = "country")
    private String country= "";

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<Order> orders = new ArrayList<Order>();

    //region Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDisplayName() {
        return String.format("%s %s", this.firstname, this.lastname);
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (lastname != null ? !lastname.equals(customer.lastname) : customer.lastname != null) return false;
        if (firstname != null ? !firstname.equals(customer.firstname) : customer.firstname != null) return false;
        if (telefonPrivate != null ? !telefonPrivate.equals(customer.telefonPrivate) : customer.telefonPrivate != null)
            return false;
        if (telefonMobile != null ? !telefonMobile.equals(customer.telefonMobile) : customer.telefonMobile != null)
            return false;
        if (fax != null ? !fax.equals(customer.fax) : customer.fax != null) return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        if (street != null ? !street.equals(customer.street) : customer.street != null) return false;
        if (houseNumber != null ? !houseNumber.equals(customer.houseNumber) : customer.houseNumber != null)
            return false;
        if (postcode != null ? !postcode.equals(customer.postcode) : customer.postcode != null) return false;
        if (city != null ? !city.equals(customer.city) : customer.city != null) return false;
        return country != null ? country.equals(customer.country) : customer.country == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (telefonPrivate != null ? telefonPrivate.hashCode() : 0);
        result = 31 * result + (telefonMobile != null ? telefonMobile.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    /**
     * Fügt dem Kunden einen Kundenauftrag hinzu und setzt den Kunden im Kundenauftrag
     * @param order Kundenauftrag
     */
    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
