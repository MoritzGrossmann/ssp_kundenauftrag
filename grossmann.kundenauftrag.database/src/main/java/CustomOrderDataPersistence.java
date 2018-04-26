import database.IPersistCustomerOrderData;
import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomOrderDataPersistence implements IPersistCustomerOrderData {

    private static final String PERSISTENCE_UNIT = "customOrderDataPersistence";

    private EntityManager getDatabaseContext() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
    }

    public List<Customer> getCustomer() {
            EntityManager context = getDatabaseContext();
            CriteriaBuilder builder = context.getCriteriaBuilder();
            CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
            Root<Customer> variableRoot = query.from(Customer.class);
            query.select(variableRoot);

            return context.createQuery(query).getResultList();
    }

    public Customer getCustomer(int id) {
        EntityManager context = getDatabaseContext();
        return context.find(Customer.class, id);
    }

    public Customer persistCustomer(Customer customer) {
        EntityManager context = getDatabaseContext();
        context.persist(customer);
        return customer;
    }
}
