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
@AllArgsConstructor
@Getter
@Setter
public class User {

    private String id;

    private String firstName;

    private String lastName;

    private String password;

    private String phone;

    private String address;

}
