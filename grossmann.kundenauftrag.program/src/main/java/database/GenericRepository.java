package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;


public class GenericRepository<T> implements Repository<T>, Serializable {

    private static final String PERSISTENCE_UNIT = "customOrderDataPersistence";

    private final EntityManager entityManager;

    private Class<T> type;

    public GenericRepository(Class<T> type) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        this.entityManager = entityManagerFactory.createEntityManager();
        this.type = type;
    }

    public GenericRepository() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        this.entityManager = entityManagerFactory.createEntityManager();
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
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public void delete(T item) {
        entityManager.remove(item);
    }

    public T update(T item) {
        entityManager.getTransaction().begin();
        T newitem = entityManager.merge(item);
        entityManager.getTransaction().commit();
        return newitem;
    }
}
