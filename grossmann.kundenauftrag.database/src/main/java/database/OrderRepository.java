package database;

import model.Order;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class OrderRepository extends GenericRepository<Order> {

    public OrderRepository() {
        super(Order.class);
    }
}
