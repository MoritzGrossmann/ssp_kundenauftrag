package database;

import model.Customer;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Named
@Stateless
public class CustomerRepository extends GenericRepository<Customer> {
    public CustomerRepository() {
        super(Customer.class);
    }

    public List<Customer> getByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        query.where(
                cb.like(
                        customerRoot.<String>get("name"),
                        cb.parameter(String.class, "likeCondition")));

        TypedQuery<Customer> tq = getEntityManager().createQuery(query);
        tq.setParameter("likeCondition", String.format("%%%s%%", name));
        return tq.getResultList();
    }
}
