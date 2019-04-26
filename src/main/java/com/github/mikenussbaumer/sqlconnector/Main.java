package com.github.mikenussbaumer.sqlconnector;

import com.github.mikenussbaumer.sqlconnector.configs.DatabaseConfig;
import com.github.mikenussbaumer.sqlconnector.entity.User;
import com.github.mikenussbaumer.sqlconnector.managers.ConfigManager;
import com.github.mikenussbaumer.sqlconnector.managers.SQLManager;
import com.github.mikenussbaumer.sqlconnector.models.UserModel;

public class Main {

    private static SQLManager sqlManager;

    public static void main ( String[] args ) {
        init( );
    }

    //<editor-fold desc="init">
    private static void init ( ) {

        sqlManager = new SQLManager( );
        sqlManager.connect( "jdbc:h2:tcp" );

        // TESTS
        UserModel userModel = new UserModel( sqlManager );

        userModel.getAll( users -> System.out.println( users.toString( ) ) );

        userModel.create( new User( 0, "Ha", "Lo", "Paswd", "phone", "add" ) );

        userModel.delete( "1" );
    }

    //</editor-fold>
    public static SQLManager getSqlManager ( ) {
        return sqlManager;
    }
}
