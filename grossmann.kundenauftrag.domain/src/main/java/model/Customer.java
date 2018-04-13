package model;

import javax.persistence.*;
import java.util.List;

public class Customer {

    @Id
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerOrder> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;
}
