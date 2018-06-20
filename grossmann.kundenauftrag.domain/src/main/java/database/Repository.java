package database;

import java.util.List;

public interface Repository<T> {

    List<T> getAll();

    T getById(int id);

    void insert(T item);

    void delete(T item);

    T update(T item);
}
