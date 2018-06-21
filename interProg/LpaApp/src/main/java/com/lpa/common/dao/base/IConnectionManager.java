//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.base;

import java.sql.Connection;

/**
 * Interface Connection Manager responsavel pelo gerenciamento da conexao
 */
public interface IConnectionManager {

    /**
     * Abre conexao
     * 
     * @param user
     * @param password
     * @param jndiName
     * @return boolean indicando se a conexao foi estabelecida
     */
    boolean open(String user, String password, String jndiName);

    /**
     * Fecha conexao se isToClose eh true
     * 
     * @param isToClose
     */
    void close(boolean isToClose);

    /**
     * Obtem uma conexao aberta
     * 
     * @return objeto Connection
     */
    Connection getConnection();

    /**
     * Registra inicio de transacao
     * 
     * @return boolean indicando se a transacao foi registrada na chamada deste metodo
     */
    boolean beginTransaction();

    /**
     * Efetua commit no banco caso isToCommit eh true
     * 
     * @param isToCommit
     */
    void commit(boolean isToCommit);

    /**
     * Efetua rollback no banco caso isToRollback eh true
     * 
     * @param isToRollback
     */
    void rollback(boolean isToRollback);

}
