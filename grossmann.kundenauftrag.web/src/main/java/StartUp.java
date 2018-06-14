import database.CustomerRepository;
import database.OrderRepository;
import database.ProductionOrderRepository;
import model.Customer;
import xml.CustomerImporter;
import xml.ParseXmlException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;
import java.util.Collection;

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
            Collection<Customer> customers = customerImporter.readFile(new File("D:\\Moritz\\Projekt\\Java\\IntelliJ\\ssp_kundenauftrag\\grossmann.kundenauftrag.xml\\src\\main\\resources\\customer.xml"));

            customers.forEach(c -> customerRepository.insert(c));
        } catch (ParseXmlException e) {
            e.printStackTrace();
        }
    }
}