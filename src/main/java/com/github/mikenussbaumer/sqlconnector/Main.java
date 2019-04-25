package com.github.mikenussbaumer.sqlconnector;

import com.github.mikenussbaumer.sqlconnector.managers.SQLManager;
import com.github.mikenussbaumer.sqlconnector.models.UserModel;

public class Main {

    private static SQLManager sqlManager;

    public static void main ( String[] args ) {
        init( );
    }

    //<editor-fold desc="init">
    private static void init ( ) {
        sqlManager = new SQLManager( "localhost", "sa", "", "test", 1521 );
        sqlManager.connect( "jdbc:h2:tcp" );

        UserModel userModel = new UserModel( sqlManager );

        userModel.get( 1, user -> System.out.println( user.toString() ) );

        userModel.getAll( users -> System.out.println( users.toString() ) );

    }
    //</editor-fold>
}
