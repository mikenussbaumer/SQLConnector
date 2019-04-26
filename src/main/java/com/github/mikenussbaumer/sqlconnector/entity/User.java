package com.github.mikenussbaumer.sqlconnector.entity;

import lombok.*;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaume.rcom)
 * @date 2019-04-25
 * @time 21:07
 * @project SQLConnector
 */
@ToString ( includeFieldNames = true )
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Getter
    private int id;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    private String password;

    @Getter
    private String phone;

    @Getter
    private String address;

    public User ( int id, String firstName, String lastName, String password, String phone, String address ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
}
