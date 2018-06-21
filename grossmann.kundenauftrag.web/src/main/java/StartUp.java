import database.CustomerRepository;
import database.OrderRepository;
import database.ProductionOrderRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class StartUp {

    @EJB
    private CustomerRepository customerRepository;

    @EJB
    private ProductionOrderRepository productionOrderRepository;

    @EJB
    private OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {

    }
}