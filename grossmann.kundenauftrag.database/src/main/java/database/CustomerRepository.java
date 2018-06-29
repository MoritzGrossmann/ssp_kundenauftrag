package database;

import model.Customer;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository zum laden, erstellen, löschen und aktuelisieren von Kunden
 */
@Named
@Stateless
public class CustomerRepository extends GenericRepository<Customer> {
    public CustomerRepository() {
        super(Customer.class);
    }

    /**
     * Lädt eine Liste von Kunden anhand von Vor- und Nachname
     * @param parts Array von String
     * @return
     */
    public List<Customer> getByName(String[] parts) {
        List<Customer> customers = new ArrayList<>();

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


    @SuppressWarnings("unchecked")
    private List<Customer> getByFirstname(String firstname) {
        String jpql = "SELECT c FROM Customer c WHERE c.firstname LIKE :firstname";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("firstname", "%" + firstname + "%");
        return query.getResultList();
    }
}
