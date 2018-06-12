package database;

import model.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class CustomerRepository extends GenericRepository<Customer> {
    public CustomerRepository() {
        super(Customer.class);
    }
}
