package com.github.mikenussbaumer.sqlconnector;

import com.github.mikenussbaumer.sqlconnector.configs.DatabaseConfig;
import com.github.mikenussbaumer.sqlconnector.entity.User;
import com.github.mikenussbaumer.sqlconnector.managers.ConfigManager;
import com.github.mikenussbaumer.sqlconnector.managers.SQLManager;
import com.github.mikenussbaumer.sqlconnector.models.UserModel;

import java.io.File;

public class Main {

    private static ConfigManager < DatabaseConfig > databaseConfig;
    private static SQLManager sqlManager;

    public static void main ( String[] args ) {
        init( );
    }

    //<editor-fold desc="init">
    private static void init ( ) {
        databaseConfig = new ConfigManager <>( new File( "databaseConfig.json" ), DatabaseConfig.class );

        sqlManager = new SQLManager(
                getDatabaseConfig().getHostname(),
                getDatabaseConfig().getUsername(),
                getDatabaseConfig().getPassword(),
                getDatabaseConfig().getDatabase(),
                getDatabaseConfig().getPort() );
        sqlManager.connect( "jdbc:h2:tcp" );

        // TESTS
        UserModel userModel = new UserModel( sqlManager );

        userModel.getAll( users -> System.out.println( users.toString( ) ) );

        userModel.create( new User( 0, "Ha", "Lo", "Paswd", "phone", "add" ) );

        userModel.delete( "1" );
    }
    //</editor-fold>


    public static DatabaseConfig getDatabaseConfig ( ) {
        return databaseConfig.getSettings( );
    }

    public static SQLManager getSqlManager ( ) {
        return sqlManager;
    }
}
