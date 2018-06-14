package database;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Named
@Stateless
public class GenericRepository<T> implements Repository<T>, Serializable {

    private static final String PERSISTENCE_UNIT = "customOrderDataPersistence";

    @PersistenceContext(name = PERSISTENCE_UNIT)
    private EntityManager entityManager;

    private Class<T> type;

    public GenericRepository(Class<T> type) {
        this.type = type;
    }

    public GenericRepository() {

    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public List<T> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        query.select(query.from(type));
        return entityManager.createQuery(query).getResultList();
    }

    public T getById(int id) {
        return entityManager.find(type, id);
    }

    public void insert(T obj) {
        entityManager.persist(obj);
    }

    public void delete(T item) {
        entityManager.remove(item);
    }

    public void update(T item) {
        entityManager.merge(item);
    }
}
