package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "production_order", schema = "customerorder")
public class ProductionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(mappedBy = "productionOrders")
    private Collection<Order> orders = new ArrayList<Order>();

    //region Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    //endregion

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
        this.orders.add(order);
        order.getProductionOrders().add(this);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
