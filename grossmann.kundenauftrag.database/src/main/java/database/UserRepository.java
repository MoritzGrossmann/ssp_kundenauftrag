package database;

import model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Named
@ApplicationScoped
public class UserRepository extends GenericRepository<User> {

    public UserRepository() {
        super(User.class);
    }



    public User getByName(String name) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("username"), name));

        List<User> result = getEntityManager().createQuery(query).getResultList();

        if(result.size() > 1 || result.size() == 0) {
            return null;
        }

        return result.get(0);
    }
}
