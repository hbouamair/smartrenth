package sample.Connection;

import java.util.List;

public interface DatabaseProvider<T> {
    T getOne(String id);

    void insert(T object);

    void update(T object);

    void delete(T object);

    List<T> getAll();

}
