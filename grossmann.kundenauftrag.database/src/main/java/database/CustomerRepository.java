package database;

import model.Customer;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class CustomerRepository extends GenericRepository<Customer> {
    public CustomerRepository() {
        super(Customer.class);
    }
}
