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

        /*
        try {
            Query query = entityManager.createNativeQuery("CREATE VIEW view_user_group AS SELECT u.username AS username, g.name AS groupname FROM user AS u INNER JOIN user_usergroup AS ug ON u.user_id = ug.user_id INNER JOIN usergroup AS g ON ug.user_group_id = g.user_group_id;");
            query.executeUpdate();
        } catch (Exception e) {
            System.err.println("View already exist");
        }

        if (customerRepository.getAll().size() == 0) {
            CustomerImporter customerImporter = new CustomerXmlImporter();
            try {
                Collection<Customer> customers = customerImporter.readFile(new File("D:\\Moritz\\Projekt\\Java\\IntelliJ\\ssp_kundenauftrag\\grossmann.kundenauftrag.xml\\src\\main\\resources\\customer.xml"));

                customers.forEach(c -> customerRepository.insert(c));
            } catch (ParseXmlException e) {
                e.printStackTrace();
            }
        }*/
    }
}