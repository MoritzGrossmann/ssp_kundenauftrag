import database.CustomerRepository;
import database.Repository;
import model.Customer;
import xml.CustomerImporter;
import xml.ParseXmlException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.util.Collection;

public class Main {

    private static File xmlFile = new File("C:\\Users\\Moritz\\IdeaProjects\\ssp_kundenauftrag\\grossmann.kundenauftrag.xml\\src\\main\\resources\\customer.xml");

    private static final String PERSISTENCE_UNIT = "customOrderDataPersistence";

    public static void main(String[] args) {
        System.out.println("Application is starting...");

        EntityManager entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();

        Repository<Customer> customerRepository = new CustomerRepository();

        if (customerRepository.getAll().size() ==0) {
            CustomerImporter customerImporter = new CustomerXmlImporter();
            try {
                Collection<Customer> customers = customerImporter.readFile(xmlFile);

                customers.forEach(c -> customerRepository.insert(c));
            } catch (ParseXmlException e) {
                e.printStackTrace();
            }
        }

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery(
                    "CREATE VIEW view_user_group AS SELECT u.username AS username, g.name AS groupname FROM user AS u " +
                            "INNER JOIN user_usergroup AS ug ON u.user_id = ug.user_id " +
                            "INNER JOIN usergroup AS g ON ug.user_group_id = g.user_group_id;");
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("View already exist");
        }

        System.out.println("Application is stopping...");

    }


}
