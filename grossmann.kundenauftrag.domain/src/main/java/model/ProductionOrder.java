package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "production_order", schema = "customerorder")
public class ProductionOrder {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToMany(mappedBy = "productionOrders", cascade = CascadeType.ALL)
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

        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result;
        return result;
    }

    public void addOrder(Order order) {
        if (this.orders == null)
            this.orders = new ArrayList<Order>();

        this.orders.add(order);
    }


    public Collection<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }
}
