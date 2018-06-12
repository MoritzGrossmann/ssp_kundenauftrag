package database;

import model.Order;

public class OrderRepository extends GenericRepository<Order> {

    public OrderRepository() {
        super(Order.class);
    }
}
