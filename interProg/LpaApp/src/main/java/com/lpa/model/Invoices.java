package com.lpa.model;

import java.util.Date;

public class Invoices extends BaseVO {

    // Table name
    public static final String lpa_table_name = "lpa_invoices";

    // Fields names
    public static final String lpa_inv_no = "lpa_inv_no";
    public static final String lpa_inv_date = "lpa_inv_date";
    public static final String lpa_inv_client_ID = "lpa_inv_client_ID";
    public static final String lpa_inv_client_name = "lpa_inv_client_name";
    public static final String lpa_inv_client_address = "lpa_inv_client_address";
    public static final String lpa_inv_amount = "lpa_inv_amount";
    public static final String lpa_inv_status = "lpa_inv_status";

    private String id;
    private String date; //DateTime
    private String client_id;
    private String name;
    private String address;
    private double amount;
    private String status;

    public Invoices() {
        super();
    }

    public Invoices(String id, String date, String client_id, String name, String address, double amount, String status) {
        this.id = id;
        this.date = date;
        this.client_id = client_id;
        this.name = name;
        this.address = address;
        this.amount = amount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
