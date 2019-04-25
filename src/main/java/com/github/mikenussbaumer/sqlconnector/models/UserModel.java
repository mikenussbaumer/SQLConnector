package com.github.mikenussbaumer.sqlconnector.models;

import com.github.mikenussbaumer.sqlconnector.entity.User;
import com.github.mikenussbaumer.sqlconnector.interfaces.DAO;

import java.util.List;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaume.rcom)
 * @date 2019-04-25
 * @time 21:07
 * @project SQLConnector
 */
public class UserModel implements DAO < User > {

    public User get ( long id ) {
        return null;
    }

    public List < User > getAll ( ) {
        return null;
    }

    public void save ( User user ) {

    }

    public void update ( User user, String[] params ) {

    }

    public void delete ( User user ) {

    }
}
