package database;

import model.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ProductionOrderRepository extends GenericRepository<Order> {
    public ProductionOrderRepository() {
        super(Order.class);
    }
}
