package database;

import model.Customer;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends GenericRepository<Customer> {
    public CustomerRepository() {
        super(Customer.class);
    }

    public List<Customer> getByName(String[] parts) {
        List<Customer> customers = new ArrayList<Customer>();

        for(String part : parts) {
            customers.addAll(getByFirstname(part));
            customers.addAll(getByLastname(part));
        }

        if (parts.length == 0) {
            return getAll();
        }

        return customers;
    }

    private List<Customer> getByLastname(String lastname) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        query.where(
                cb.like(
                        customerRoot.<String>get("lastname"),
                        cb.parameter(String.class, "likeCondition")));

        TypedQuery<Customer> tq = getEntityManager().createQuery(query);
        tq.setParameter("likeCondition", String.format("%%%s%%", lastname));
        return tq.getResultList();
    }

    private List<Customer> getByFirstname(String firstname) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        query.where(
                cb.like(
                        customerRoot.<String>get("firstname"),
                        cb.parameter(String.class, "likeCondition")));

        TypedQuery<Customer> tq = getEntityManager().createQuery(query);
        tq.setParameter("likeCondition", String.format("%%%s%%", firstname));
        return tq.getResultList();
    }
}
