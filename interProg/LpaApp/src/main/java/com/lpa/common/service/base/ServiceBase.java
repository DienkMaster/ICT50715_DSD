//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.service.base;

import com.ford.bars.common.dao.base.DAOBase;
import com.ford.bars.common.dao.base.IConnectionManager;
import com.ford.bars.common.layer.exception.BARSRuntimeException;
import com.lpa.common.service.Command;

public interface ServiceBase {

    void setConnectionManager(IConnectionManager connectionManager);

    IConnectionManager getConnectionManager();

    <T> T transactionAndClose(Command<T> command) throws BARSRuntimeException;

    <T> T transactionAndClose(Command<T> command, DAOBase dao) throws BARSRuntimeException;

    <T> T executeAndClose(Command<T> command) throws BARSRuntimeException;

    <T> T executeAndClose(Command<T> command, DAOBase dao) throws BARSRuntimeException;

    String testConnection();

    Boolean getConnection(IConnectionManager connectionManager);
}
