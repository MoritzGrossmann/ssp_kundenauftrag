import database.CustomerRepository;
import model.Customer;
import xml.CustomerImporter;
import xml.ParseXmlException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.File;
import java.util.List;

@Singleton
@Startup
public class StartUp {

    @Inject
    private
    CustomerRepository customerRepository;

    @PostConstruct
    public void init() {

        CustomerImporter customerImporter = new CustomerXmlImporter();

        try {
            List<Customer> customers = customerImporter.readFile(new File("D:\\Moritz\\Projekt\\Java\\IntelliJ\\ssp_kundenauftrag\\grossmann.kundenauftrag.xml\\src\\main\\resources\\customer.xml"));
            customers.forEach(c -> {
                c.getOrders().forEach(o -> o.setCustomer(c));

                if (customerRepository.getById(c.getId()) == null)
                    customerRepository.insert(c);
            });

        } catch (ParseXmlException e) {
            e.printStackTrace();
        }
    }
}