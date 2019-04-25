package com.github.mikenussbaumer.sqlconnector;

import com.github.mikenussbaumer.sqlconnector.managers.SQLManager;

public class Main {

    private static SQLManager sqlManager;

    public static void main ( String[] args ) {
        init( );
    }

    //<editor-fold desc="init">
    private static void init ( ) {
        sqlManager = new SQLManager( "localhost", "sa", "", "opt/h2-data/test", 1521 );
        sqlManager.connect( "jdbc:h2:tcp" );


    }
    //</editor-fold>
}
