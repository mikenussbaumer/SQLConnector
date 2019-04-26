package com.github.mikenussbaumer.sqlconnector.interfaces;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaume.rcom)
 * @date 2019-04-25
 * @time 21:04
 * @project SQLConnector
 */
public interface DAO< T > {

    void get ( int id, Consumer<T> consumer );

    void getAll ( Consumer<List<T>> consumer );

    void create ( T t );

    void update ( T t, String[] params );

    void delete ( String id );

}
