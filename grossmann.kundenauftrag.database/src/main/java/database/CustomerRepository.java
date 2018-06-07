package database;

import database.Repository;
import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerRepository extends CustomOrderDatabase implements Repository<Customer> {

    public List<Customer> getAll() {
        EntityManager entityManager = getDatabaseContext();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> variableRoot = query.from(Customer.class);
        query.select(variableRoot);

        return entityManager.createQuery(query).getResultList();
    }

    public Customer getById(int id) {
        EntityManager entityManager = getDatabaseContext();
        return entityManager.find(Customer.class, id);
    }

    public void insert(Customer item) {
        EntityManager context = getDatabaseContext();
        context.getTransaction().begin();
        context.persist(item);
        context.getTransaction().commit();
    }

    public void delete(Customer item) {
        EntityManager entityManager = getDatabaseContext();
        entityManager.getTransaction().begin();
        entityManager.remove(item);
        entityManager.getTransaction().commit();
    }

    public void update(Customer item) {
        EntityManager entityManager = getDatabaseContext();
        Customer customer = entityManager.find(Customer.class, item.getId());
        entityManager.getTransaction().begin();
        customer.setName(item.getName());
        customer.setOrders(item.getOrders());
        entityManager.getTransaction().commit();
    }
}
