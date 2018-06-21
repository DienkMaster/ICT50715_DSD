//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.service;

import java.sql.Connection;

import com.ford.bars.common.layer.exception.BARSRuntimeException;

public abstract class Command<T> {

    private Connection connection;

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

    public abstract T execute() throws BARSRuntimeException;

}
