package database;

import model.OrderItem;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class OrderItemRepository extends GenericRepository<OrderItem> {
    public OrderItemRepository() {
        super(OrderItem.class);
    }
}
