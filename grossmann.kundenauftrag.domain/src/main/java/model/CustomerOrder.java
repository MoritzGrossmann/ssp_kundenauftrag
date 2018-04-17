package model;

import javax.persistence.*;

@Entity
public class CustomerOrder {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
