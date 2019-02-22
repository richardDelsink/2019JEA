package dao;

import java.util.ArrayList;

public interface GenericInterface<T> {
    void add(T t);
    void remove(T t);
    void update(T t);
    T findByName(String name);
}
