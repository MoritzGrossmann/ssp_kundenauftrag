package model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "production_order", schema = "customerorder")
public class ProductionOrder {

    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "customer_order_id")
    private int customerOrderId;

    @ManyToMany(mappedBy = "productionOrders", cascade = CascadeType.PERSIST)
    private Collection<Order> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductionOrder that = (ProductionOrder) o;

        if (id != that.id) return false;
        return customerOrderId == that.customerOrderId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + customerOrderId;
        return result;
    }


    public Collection<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }
}
