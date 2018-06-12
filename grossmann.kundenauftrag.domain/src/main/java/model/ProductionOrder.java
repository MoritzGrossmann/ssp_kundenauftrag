package model;

import javax.persistence.*;

@Entity
@Table(name = "production_order", schema = "customerorder", catalog = "")
public class ProductionOrder {
    private int id;
    private Order customerOrder;

    @Id
    @Column(name = "id")
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
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id", nullable = false)
    public Order getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(Order customerOrder) {
        this.customerOrder = customerOrder;
    }
}
