package database;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Generisches Repository zum erstellen, laden, löschen und ändern von Entitäten aus der Datenbank
 * @param <T>
 */
@Named
@Stateless
public class GenericRepository<T> implements Repository<T>, Serializable {

    private static final String PERSISTENCE_UNIT = "customOrderDataPersistence";

    @PersistenceContext(name = PERSISTENCE_UNIT)
    private EntityManager entityManager;

    private Class<T> type;

    /**
     *
     * @param type Klassenname der Entität
     */
    public GenericRepository(Class<T> type) {
        this.type = type;
    }

    public GenericRepository() {

    }

    /**
     * Gibt die Instanz des Entitymanagers zurück
     * @return
     */
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * Gibt alle Entitäten einer Klasse zurück
     * @return
     */
    public List<T> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        query.select(query.from(type));
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Gibt die Entität mit dem Primärschlüssel id zurück
     * @param id Primärschlüssel der Entität
     * @return
     */
    public T getById(int id) {
        T item = entityManager.find(type, id);
        return item;
    }

    /**
     * Erstellt eine neue Entität in der Datenbank
     * @param item Entität, welche in der Datenbank erstellt werden soll
     */
    public void insert(T item) {
        entityManager.persist(item);
        entityManager.flush();
    }

    /**
     * Löscht eine Entität aus der Datenbank
     * @param item Entität, welche gelöscht werden soll
     */
    public void delete(T item) {
        T i = update(item);
        entityManager.remove(i);
        entityManager.flush();
    }

    /**
     * Aktualisiert eine Entität in der Datenbank und gibt die aktualisierte Version zurück
     * @param item Entität, welche in der Datenbank aktualisiert werden soll
     * @return
     */
    public T update(T item) {
        return entityManager.merge(item);
    }
}
