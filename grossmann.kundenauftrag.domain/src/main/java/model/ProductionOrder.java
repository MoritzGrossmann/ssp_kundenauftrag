package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProductionOrder {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="prodOrderItems",
            joinColumns=@JoinColumn(name="poID", referencedColumnName="poID"),
            inverseJoinColumns=@JoinColumn(name="productID", referencedColumnName="pID"))
    private List<Product> products;
}
