package database;

import model.UserGroup;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserGroupRepository extends GenericRepository<UserGroup> {

    public UserGroupRepository() {
        super(UserGroup.class);
    }

    public UserGroup getByName(String name) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserGroup> query = builder.createQuery(UserGroup.class);
        Root<UserGroup> root = query.from(UserGroup.class);
        query.select(root).where(builder.equal(root.get("name"), name));

        List<UserGroup> result = getEntityManager().createQuery(query).getResultList();

        if(result.size() > 1 || result.size() == 0) {
            return null;
        }

        return result.get(0);
    }
}
