package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "customer_order", schema = "customerorder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "date_time")
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<ProductionOrder> productionOrders = new ArrayList<ProductionOrder>();

    //region Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<ProductionOrder> getProductionOrders() {
        return productionOrders;
    }

    public void setProductionOrders(Collection<ProductionOrder> productionOrders) {
        this.productionOrders = productionOrders;
    }

    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        return dateTime != null ? dateTime.equals(order.dateTime) : order.dateTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

    public void addProductionOrder(ProductionOrder productionOrder) {
        this.productionOrders.add(productionOrder);
        productionOrder.setOrder(this);
    }

}
