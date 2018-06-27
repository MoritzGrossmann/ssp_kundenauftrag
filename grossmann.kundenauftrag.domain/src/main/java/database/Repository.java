package database;

import java.util.List;

/**
 * Generisches Repository zum Laden, Anlegen, Löschen und Aktualisieren von Entitäten einer Klasse T
 * @param <T> Klasse der Entitäten
 */
public interface Repository<T> {

    /**
     * Gibt alle Entitäten einer Klasse zurück
     * @return
     */
    List<T> getAll();

    /**
     * Gibt die Entität mit dem Primärschlüssel id zurück
     * @param id Primärschlüssel der Entität
     * @return
     */
    T getById(int id);

    /**
     * Erstellt eine neue Entität in der Datenbank
     * @param item Entität, welche in der Datenbank erstellt werden soll
     */
    void insert(T item);

    /**
     * Löscht eine Entität aus der Datenbank
     * @param item Entität, welche gelöscht werden soll
     */
    void delete(T item);

    /**
     * Aktualisiert eine Entität in der Datenbank und gibt die aktualisierte Version zurück
     * @param item Entität, welche in der Datenbank aktualisiert werden soll
     * @return
     */
    T update(T item);
}
