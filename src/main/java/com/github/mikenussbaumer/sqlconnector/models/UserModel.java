package com.github.mikenussbaumer.sqlconnector.models;

import com.github.mikenussbaumer.sqlconnector.entity.User;
import com.github.mikenussbaumer.sqlconnector.interfaces.DAO;
import com.github.mikenussbaumer.sqlconnector.managers.SQLManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaume.rcom)
 * @date 2019-04-25
 * @time 21:07
 * @project SQLConnector
 */
public class UserModel implements DAO < User > {

    private SQLManager sqlManager;

    public UserModel ( SQLManager sqlManager ) {
        this.sqlManager = sqlManager;
    }

    /**
     * Get a specific user from the database by id
     * @param id
     * @param consumer
     */
    @Override
    public void get ( int id, Consumer < User > consumer ) {
        try {
            ResultSet resultSet = sqlManager.query( "SELECT * FROM `USERS`"
                    + " WHERE `id`='" + id + "'" );
            User result = null;
            while ( resultSet.next( ) ) {
                result = new User(
                        id,
                        resultSet.getString( "firstName" ),
                        resultSet.getString( "lastName" ),
                        resultSet.getString( "password" ),
                        resultSet.getString( "phone" ),
                        resultSet.getString( "address" )
                );
            }

            consumer.accept( result );
            resultSet.close( );
        } catch ( SQLException error ) {
            error.printStackTrace( );
        }
    }

    /**
     * Get all users from the database
     * @param consumer
     */
    @Override
    public void getAll ( Consumer < List < User > > consumer ) {
        try {
            ResultSet resultSet = sqlManager.query( "SELECT * FROM `USERS`" );
            List < User > result = new ArrayList <>( );
            while ( resultSet.next( ) ) {
                result.add( new User(
                        resultSet.getInt( "id" ),
                        resultSet.getString( "firstName" ),
                        resultSet.getString( "lastName" ),
                        resultSet.getString( "password" ),
                        resultSet.getString( "phone" ),
                        resultSet.getString( "address" )
                ) );
            }

            consumer.accept( result );
            resultSet.close( );

        } catch ( SQLException error ) {
            error.printStackTrace( );
        }
    }

    /**
     * Insert a user into the database
     * @param user
     */
    @Override
    public void create ( User user ) {
        String query = "INSERT INTO `USERS` (`firstName`, `lastName`, `password`, `phone`, `address`) VALUES (" +
                "'" + user.getFirstName( ) + "', " +
                "'" + user.getLastName( ) + "', " +
                "'" + user.getPassword( ) + "', " +
                "'" + user.getPhone( ) + "', " +
                "'" + user.getAddress( ) + "');";
        sqlManager.update( query );
    }

    /**
     * Update a specific user in the database
     * @param user
     * @param params
     */
    @Override
    public void update ( User user, String[] params ) {

    }

    /**
     * Delete a specific user in the database by id
     * @param id
     */
    @Override
    public void delete ( String id ) {
        String query = "DELETE FROM `USERS` WHERE `id`='" + id + "'";
        sqlManager.update( query );
    }
}
