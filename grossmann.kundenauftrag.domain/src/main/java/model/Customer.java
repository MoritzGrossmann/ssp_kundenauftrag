package model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Customer {
    private int id;
    private String name;
    private String telefonPrivate;
    private String telefonMobile;
    private String fax;
    private String email;
    private String street;
    private String houseNumber;
    private String postcode;
    private String city;
    private String country;
    private Collection<Order> orders;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "telefon_private")
    public String getTelefonPrivate() {
        return telefonPrivate;
    }

    public void setTelefonPrivate(String telefonPrivate) {
        this.telefonPrivate = telefonPrivate;
    }

    @Basic
    @Column(name = "telefon_mobile")
    public String getTelefonMobile() {
        return telefonMobile;
    }

    public void setTelefonMobile(String telefonMobile) {
        this.telefonMobile = telefonMobile;
    }

    @Basic
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "house_number")
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Basic
    @Column(name = "postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
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
        result = 31 * result + (name != null ? name.hashCode() : 0);
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }
}
