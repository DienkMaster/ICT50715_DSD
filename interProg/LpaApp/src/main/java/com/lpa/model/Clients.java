package com.lpa.model;

import java.util.Date;
import java.util.Objects;

public class Clients extends BaseVO {

    // Table name
    public static final String lpa_table_name = "lpa_clients";

    // Fields names
    public static final String lpa_client_ID = "lpa_client_ID";
    public static final String lpa_client_firstname = "lpa_client_firstname";
    public static final String lpa_client_lastname = "lpa_client_lastname";
    public static final String lpa_client_address = "lpa_client_address";
    public static final String lpa_client_phone = "lpa_client_phone";
    public static final String lpa_client_status = "lpa_client_status";

    private String id;
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private String status;

    public Clients() {
        super();
    }

    public Clients(String id, String firstname, String lastname, String address, String phone, String status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
