import database.CustomOrderPersistence;
import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomOrderDatabase implements CustomOrderPersistence {

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

    public List<Customer> getCustomer(String namePart) {
        EntityManager context = getDatabaseContext();
        CriteriaBuilder cb = context.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        query.where(
                cb.like(
                        //assuming 'lastName' is the property on the Person Java object that is mapped to the last_name column on the Person table.
                        customerRoot.<String>get("name"),
                        //Add a named parameter called likeCondition
                        cb.parameter(String.class, "likeCondition")));

        TypedQuery<Customer> tq = context.createQuery(query);
        tq.setParameter("likeCondition", String.format("%%%s%%", namePart));
        List<Customer> customers = tq.getResultList();
        return customers;
    }

    public Customer getCustomer(int id) {
        EntityManager context = getDatabaseContext();
        return context.find(Customer.class, id);
    }

    public Customer persistCustomer(Customer customer) {
        EntityManager context = getDatabaseContext();
        context.getTransaction().begin();
        context.persist(customer);
        context.getTransaction().commit();
        return customer;
    }
}
