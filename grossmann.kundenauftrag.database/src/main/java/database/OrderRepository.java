package database;

import model.Order;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Repository zum laden, erstellen, löschen und aktuelisieren von Kundenaufträgen
 */
@Named
@Stateless
public class OrderRepository extends GenericRepository<Order> {

    public OrderRepository() {
        super(Order.class);
    }
}
