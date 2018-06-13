import database.CustomerRepository;
import database.OrderRepository;
import database.ProductionOrderRepository;
import model.Customer;
import model.ProductionOrder;
import xml.CustomerImporter;
import xml.ParseXmlException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
@Startup
public class StartUp {

    @EJB
    private CustomerRepository customerRepository;

    @EJB
    private ProductionOrderRepository productionOrderRepository;

    @EJB
    private OrderRepository orderRepository;

    @PostConstruct
    public void init() {

        CustomerImporter customerImporter = new CustomerXmlImporter();

        try {
            Map<Integer,ProductionOrder> productionOrders = new HashMap<>();

            List<Customer> customers = customerImporter.readFile(new File("D:\\Moritz\\Projekt\\Java\\IntelliJ\\ssp_kundenauftrag\\grossmann.kundenauftrag.xml\\src\\main\\resources\\customer.xml"));
            customers.forEach(c -> {
                c.getOrders().forEach(o -> {
                    o.getProductionOrders().forEach(p -> {
                        if (productionOrders.containsKey(p.getId())) {
                            productionOrders.get(p.getId()).addOrder(o);
                        } else {
                            p.addOrder(o);
                            productionOrders.put(p.getId(), p);
                        }
                    });
                });
            });

            customerRepository.insert(customers.get(0));
        } catch (ParseXmlException e) {
            e.printStackTrace();
        }
    }
}