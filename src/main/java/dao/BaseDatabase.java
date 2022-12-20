package dao;

import java.util.List;
import java.util.Objects;

public interface BaseDatabase<T> {
    boolean addObject(T t);

    List<T> getObjectList();

    default String forSql(String name){
        return "'" + name + "'";
    }
}
