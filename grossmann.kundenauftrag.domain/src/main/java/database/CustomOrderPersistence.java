package database;

import model.Customer;

import java.util.List;

public interface CustomOrderPersistence {

    /**
     * Returns all known Customers in the PErsistence
     * @return
     */
    List<Customer> getCustomer();

    /**
     * Returns a single Customer with id = @id from the Persistence
     * @param id
     * @return
     */
    Customer getCustomer(int id);

    Customer persistCustomer(Customer customer);

}
