package database;

import model.ProductionOrder;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class ProductionOrderRepository extends GenericRepository<ProductionOrder> {
    public ProductionOrderRepository() {
        super(ProductionOrder.class);
    }
}
