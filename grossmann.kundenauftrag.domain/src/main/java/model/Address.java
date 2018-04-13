package model;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="firstname")
    private String firstname;

    @Column(name="company")
    private String company;

    @Column(name="city")
    private String city;

    @Column(name="street")
    private String street;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;
}
