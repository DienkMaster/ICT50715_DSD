package com.lpa.model;

import java.util.Date;

public class Stock extends BaseVO {

    // Table name
    public static final String lpa_table_name = "lpa_stock";

    // Fields names
    public static final String lpa_stock_ID = "lpa_stock_ID";
    public static final String lpa_stock_name = "lpa_stock_name";
    public static final String lpa_stock_desc = "lpa_stock_desc";
    public static final String lpa_stock_onhand = "lpa_stock_onhand";
    public static final String lpa_stock_price = "lpa_stock_price";
    public static final String lpa_stock_status = "lpa_stock_status";

    private String id;
    private String name;
    private String desc;
    private String onhand;
    private double price;
    private String status;

    public Stock() {
        super();
    }

    public Stock(String id, String name, String desc, String onhand, double price, String status) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.onhand = onhand;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOnhand() {
        return onhand;
    }

    public void setOnhand(String onhand) {
        this.onhand = onhand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
