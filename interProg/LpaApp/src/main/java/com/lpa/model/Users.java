package com.lpa.model;

import java.util.Date;

public class Users extends BaseVO {

    // Table name
    public static final String lpa_table_name = "lpa_users";

    // Fields names
    public static final String lpa_user_ID = "lpa_user_ID";
    public static final String lpa_user_username = "lpa_user_username";
    public static final String lpa_user_password = "lpa_user_password";
    public static final String lpa_user_firstname = "lpa_user_firstname";
    public static final String lpa_user_lastname = "lpa_user_lastname";
    public static final String lpa_user_group = "lpa_user_group";
    public static final String lpa_inv_status = "lpa_inv_status";

    private String id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String group;
    private String status;

    public Users() {
        super();
    }

    public Users(String id, String username, String password, String firstname, String lastname, String group, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.group = group;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
