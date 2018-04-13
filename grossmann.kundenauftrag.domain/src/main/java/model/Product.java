package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="prodOrderItems",
            joinColumns=@JoinColumn(name="productID", referencedColumnName="pID"),
            inverseJoinColumns=@JoinColumn(name="poID", referencedColumnName="poID"))
    private List<ProductionOrder> productionOrders;

}
