package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "customer_order", schema = "customerorder", catalog = "")
public class Order {
    private int id;
    private Date dateTime;
    private Customer customer;
    private Collection<ProductionOrder> productionOrders;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_time")
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (dateTime != null ? !dateTime.equals(order.dateTime) : order.dateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(mappedBy = "customerOrder")
    public Collection<ProductionOrder> getProductionOrders() {
        return productionOrders;
    }

    public void setProductionOrders(Collection<ProductionOrder> productionOrders) {
        this.productionOrders = productionOrders;
    }
}
