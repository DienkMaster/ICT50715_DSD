package com.lpa.common.dao.base;

import java.sql.Connection;

/**
 * Interface DAO base
 */
public interface DAOBase {

    /**
     * Atribui conexao
     * 
     * @param connection - conexao
     */
    void setConnection(Connection connection);

    /**
     * Retorna conexao
     * 
     * @return Connection
     */
    Connection getConnection();

    /**
     * Efetua teste de conexao com o banco de dados
     * 
     * @return String
     */
    String testConnection();

}
