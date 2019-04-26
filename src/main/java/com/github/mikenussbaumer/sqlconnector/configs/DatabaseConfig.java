package com.github.mikenussbaumer.sqlconnector.configs;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaume.rcom)
 * @date 2019-04-26
 * @time 11:04
 * @project SQLConnector
 */
public class DatabaseConfig {

    @Getter
    @Setter
    private String hostname = "localhost";

    @Getter
    @Setter
    private String username = "sa";

    @Getter
    @Setter
    private String password = "";

    @Getter
    @Setter
    private String database = "test";

    @Getter
    @Setter
    private int port = 1521;
}
