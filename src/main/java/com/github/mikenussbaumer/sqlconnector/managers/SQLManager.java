package com.github.mikenussbaumer.sqlconnector.managers;

import com.github.mikenussbaumer.sqlconnector.configs.DatabaseConfig;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.sql.*;
import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaume.rcom)
 * @date 2019-04-25
 * @time 20:31
 * @project SQLConnector
 */
@Getter
@Setter
public class SQLManager {

    private static ConfigManager < DatabaseConfig > databaseConfig;
    private String hostname, username, password, database;
    private int port;

    private Connection connection;
    private static ExecutorService executorService = Executors.newCachedThreadPool( );

    public SQLManager ( ) {
        createConfig();
        this.hostname = getDatabaseConfig().getHostname();
        this.username = getDatabaseConfig().getUsername();
        this.password = getDatabaseConfig().getPassword();
        this.database = getDatabaseConfig().getDatabase();
        this.port = getDatabaseConfig().getPort();
    }

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
            String uri = MessageFormat.format( "{0}://{1}:{2}/{3}", driverName, hostname, String.valueOf( port ), database );

            this.connection = DriverManager.getConnection( uri, username, password );

            if ( !this.connection.isClosed( ) && connection != null ) {
                System.out.println( "[SQLManager] Successfully connected to Database: " + uri );
            }
        } catch ( SQLException error ) {
            System.err.println( "[SQLManager] Can't connect to Database: " + error.getMessage( ) );
        }
    }
    //</editor-fold>

    //<editor-fold desc="close">
    public void close ( ) {
        try {
            if ( !connection.isClosed( ) && connection != null ) {
                connection.close( );
                executorService.shutdown( );

                if ( connection.isClosed( ) ) {
                    System.out.println( "[SQLManager] Connection successfully closed." );
                } else {
                    System.err.println( "[SQLManager] Can't close the mysql connection" );
                }
            }
        } catch ( SQLException error ) {
            System.err.println( "[SQLManager] Can't close the mysql connection: " + error.getMessage( ) );
        }
    }
    //</editor-fold>

    //<editor-fold desc="update">
    public void update ( String query ) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement( query );
            preparedStatement.executeUpdate( );
            closePreparedStatement( preparedStatement );
        } catch ( SQLException error ) {
            System.err.println( "[SQLManager] Update error: " + error.getMessage( ) );
        }
    }
    //</editor-fold>@@ﬂﬂﬂ@

    //<editor-fold desc="multiUpdate">
    public void multiUpdate ( String... multiquery ) {
        for ( String query : multiquery ) {
            this.update( query );
        }
    }
    //</editor-fold>

    //<editor-fold desc="query">
    public ResultSet query ( String query ) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement( query );
            resultSet = preparedStatement.executeQuery( );
            closePreparedStatement( preparedStatement );
        } catch ( SQLException error ) {
            System.err.println( "[SQLManager] Query error: " + error.getMessage( ) );
        }
        return resultSet;
    }
    //</editor-fold>

    //<editor-fold desc="closePreparedStatement">
    private void closePreparedStatement ( PreparedStatement preparedStatement ) {
        executorService.execute( new Runnable( ) {
            @Override
            public void run ( ) {
                try {
                    Thread.sleep( 20000 );
                    preparedStatement.close( );
                } catch ( Exception e ) {
                    e.printStackTrace( );
                }
            }
        } );
    }
    //</editor-fold>

    //<editor-fold desc="createConfig">
    private void createConfig() {
        databaseConfig = new ConfigManager <>( new File( "databaseConfig.json" ), DatabaseConfig.class );
    }
    //</editor-fold>

    //<editor-fold desc="getDatabaseConfig">
    public static DatabaseConfig getDatabaseConfig ( ) {
        return databaseConfig.getSettings();
    }
    //</editor-fold>
}
