package com.github.mikenussbaumer.sqlconnector.interfaces;

import java.util.List;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaume.rcom)
 * @date 2019-04-25
 * @time 21:04
 * @project SQLConnector
 */
public interface DAO< T > {

    T get ( long id );

    List < T > getAll ( );

    void save ( T t );

    void update ( T t, String[] params );

    void delete ( T t );

}
