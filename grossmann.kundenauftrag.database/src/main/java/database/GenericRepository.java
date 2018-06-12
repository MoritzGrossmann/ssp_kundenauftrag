package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GenericRepository<T> implements Repository<T> {

    private static final String PERSISTENCE_UNIT = "customOrderDataPersistence";

    //@PersistenceContext
    private EntityManager entityManager;

    private Class<T> type;

    public GenericRepository(Class<T> type) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        this.entityManager = entityManagerFactory.createEntityManager();
        this.type = type;
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

    }

    public void update(T item) {

    }
}
