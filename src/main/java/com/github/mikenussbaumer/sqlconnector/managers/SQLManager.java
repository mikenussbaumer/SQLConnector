package com.github.mikenussbaumer.sqlconnector.managers;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.text.MessageFormat;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaume.rcom)
 * @date 2019-04-25
 * @time 20:31
 * @project SQLConnector
 */
@Getter
@Setter
public class SQLManager {

    private String hostname, username, password, database;
    private int port;

    private Connection connection;

    public SQLManager ( String hostname, String username, String password, String database, int port ) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;
    }

    //<editor-fold desc="connect">
    public void connect ( String driverName ) {
        try {
            String uri = MessageFormat.format ( "{0}://{1}:{2}/{3}", driverName, hostname, port, database );

            this.connection = DriverManager.getConnection ( uri, username, password );

            if ( !this.connection.isClosed ( ) && connection != null ) {
                System.out.println ( "[SQLManager] Successfully connected to Database: " + uri );
            }
        } catch ( SQLException error ) {
            System.err.println ( "[SQLManager] Can't connect to Database: " + error.getMessage ( ) );
        }
    }
    //</editor-fold>

    //<editor-fold desc="close">
    public void close ( ) {
        try {
            if ( !connection.isClosed ( ) && connection != null ) {
                connection.close ( );

                if ( connection.isClosed ( ) ) {
                    System.out.println ( "[SQLManager] Connection successfully closed." );
                } else {
                    System.err.println ( "[SQLManager] Can't close the mysql connection" );
                }
            }
        } catch ( SQLException error ) {
            System.err.println ( "[SQLManager] Can't close the mysql connection: " + error.getMessage ( ) );
        }
    }
    //</editor-fold>

    //<editor-fold desc="update">
    public void update ( String query ) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement ( query );
            preparedStatement.executeUpdate ( );
        } catch ( SQLException error ) {
            System.err.println ( "[SQLManager] Update error: " + error.getMessage ( ) );
        }
    }
    //</editor-fold>@@ﬂﬂﬂ@

    //<editor-fold desc="multiUpdate">
    public void multiUpdate ( String... multiquery ) {
        for ( String query : multiquery ) {
            this.update ( query );
        }
    }
    //</editor-fold>

    //<editor-fold desc="query">
    public ResultSet query ( String query ) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement ( query );
            resultSet = preparedStatement.executeQuery ( );
        } catch ( SQLException error ) {
            System.err.println ( "[SQLManager] Query error: " + error.getMessage ( ) );
        }
        return resultSet;
    }
    //</editor-fold>
}
