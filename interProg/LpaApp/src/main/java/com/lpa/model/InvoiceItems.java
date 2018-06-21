package com.lpa.model;

import java.util.Date;

public class InvoiceItems extends BaseVO {

    // Table name
    public static final String lpa_Table_name = "lpa_invoice_items";

    // Fields names
    public static final String lpa_invitem_no = "lpa_invitem_no";
    public static final String lpa_invitem_inv_no = "lpa_invitem_inv_no";
    public static final String lpa_invitem_stock_ID = "lpa_invitem_stock_ID";
    public static final String lpa_invitem_stock_name = "lpa_invitem_stock_name";
    public static final String lpa_invitem_qty = "lpa_invitem_qty";
    public static final String lpa_invitem_stock_price = "lpa_invitem_stock_price";
    public static final String lpa_invitem_stock_amount = "lpa_invitem_stock_amount";
    public static final String lpa_inv_status = "lpa_inv_status";

    private String no;
    private String invNo;
    private String stockId;
    private String stockName;
    private String qty;
    private double price;
    private double amount;
    private String status;

    public InvoiceItems() {
        super();
    }

    public InvoiceItems(String no, String invNo, String stockId, String stockName, String qty, double price, double amount, String status) {
        this.no = no;
        this.invNo = invNo;
        this.stockId = stockId;
        this.stockName = stockName;
        this.qty = qty;
        this.price = price;
        this.amount = amount;
        this.status = status;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
