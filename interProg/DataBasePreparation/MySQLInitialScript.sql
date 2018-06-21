CREATE DATABASE LPA_eComms;

USE LPA_eComms;
CREATE TABLE LPA_eComms.lpa_stock(
	lpa_stock_ID VARCHAR(20) NOT NULL,
    PRIMARY KEY(lpa_stock_ID),
	lpa_stock_name VARCHAR(250),
	lpa_stock_desc TEXT,
	lpa_stock_onhand VARCHAR(5),
	lpa_stock_price DECIMAL(7, 2),
	lpa_stock_status CHAR(1)
);

USE LPA_eComms;
CREATE TABLE LPA_eComms.lpa_clients (
    lpa_client_ID VARCHAR(20) NOT NULL,
    PRIMARY KEY(lpa_client_ID),
    lpa_client_firstname VARCHAR(50),
    lpa_client_lastname VARCHAR(50),
    lpa_client_address VARCHAR(250),
    lpa_client_phone VARCHAR(30),
    lpa_client_status CHAR(1)
);

USE LPA_eComms;
CREATE TABLE LPA_eComms.lpa_invoices(
	lpa_inv_no VARCHAR(20) NOT NULL,
    PRIMARY KEY(lpa_inv_no),
	lpa_inv_date DATETIME,
	lpa_inv_client_ID VARCHAR(20),
    FOREIGN KEY (lpa_inv_client_ID) REFERENCES LPA_eComms.lpa_clients(lpa_client_ID),
	lpa_inv_client_name VARCHAR(50),
	lpa_inv_client_address VARCHAR(250),
	lpa_inv_amount DECIMAL(8, 2),
	lpa_inv_status CHAR(1)
);

USE LPA_eComms;
CREATE TABLE LPA_eComms.lpa_invoice_items(
	lpa_invitem_no VARCHAR(20) NOT NULL,
    PRIMARY KEY(lpa_invitem_no),
	lpa_invitem_inv_no VARCHAR(20),
    FOREIGN KEY (lpa_invitem_inv_no) REFERENCES LPA_eComms.lpa_invoices(lpa_inv_no),
	lpa_invitem_stock_ID VARCHAR(20),
    FOREIGN KEY (lpa_invitem_stock_ID) REFERENCES LPA_eComms.lpa_stock(lpa_stock_ID),
	lpa_invitem_stock_name VARCHAR(250),
	lpa_invitem_qty” VARCHAR(6),
	lpa_invitem_stock_price DECIMAL(7, 2),
	lpa_invitem_stock_amount DECIMAL(7, 2),
	lpa_inv_status CHAR(1)
);

USE LPA_eComms;
CREATE TABLE LPA_eComms.lpa_users(
	lpa_user_ID VARCHAR(20) NOT NULL,
	PRIMARY KEY(lpa_user_ID),
    lpa_user_username VARCHAR(30),
	lpa_user_password VARCHAR(50),
	lpa_user_firstname VARCHAR(50),
	lpa_user_lastname VARCHAR(50),
	lpa_user_group VARCHAR(50),
	lpa_inv_status CHAR(1)
)