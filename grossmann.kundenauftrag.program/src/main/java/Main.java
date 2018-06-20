import database.CustomerRepository;
import database.Repository;
import model.Customer;
import xml.CustomerImporter;
import xml.ParseXmlException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.util.Collection;

public class Main {

    private static File xmlFile = new File("C:\\Users\\Moritz\\IdeaProjects\\ssp_kundenauftrag\\grossmann.kundenauftrag.xml\\src\\main\\resources\\customer.xml");

    private static final String PERSISTENCE_UNIT = "customOrderDataPersistence";

    public static void main(String[] args) {
        System.out.println("Application is starting...");

        EntityManager entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();

        Repository<Customer> customerRepository = new CustomerRepository();

        CustomerImporter customerImporter = new CustomerXmlImporter();
        try {
            Collection<Customer> customers = customerImporter.readFile(xmlFile);

            customers.forEach(c -> customerRepository.insert(c));
        } catch (ParseXmlException e) {
            e.printStackTrace();
        }

        System.out.println("Application is stopping...");

    }


}
