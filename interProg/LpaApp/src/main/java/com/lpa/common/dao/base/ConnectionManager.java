//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.base;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ford.it.exception.FordExceptionAttributes;
import com.ford.it.logging.ILogger;
import com.ford.it.logging.Level;
import com.ford.it.logging.LogFactory;

public class ConnectionManager implements IConnectionManager {

    /** Logging. */
    private static final String CLASS_NAME = ConnectionManager.class.getName();

    /** Logging. */
    private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);

    private Connection conn;

    private ConnectionManager() {
    }

    public static ConnectionManager newInstance() {
        return new ConnectionManager();
    }

    public static ConnectionManager newInstance(final Connection conn) {
        final ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.conn = conn;
        return connectionManager;
    }

    public boolean open(final String user, final String password, final String jndiName) {
        final String METHOD_NAME = "open";

        DataSource dataSource = null;

        try {

            if (this.conn == null || this.conn.isClosed()) {
                dataSource = ConnectionHelper.jndiLookup(jndiName);
                this.conn = dataSource.getConnection(user, password);
                this.conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                return true;
            }

            return false;

        } catch (final NamingException e) {
            log.log(Level.SEVERE, "Jndi Lookup for " + jndiName + " threw an error:");
            log.log(Level.SEVERE, e.getMessage(), e);
            throw new BARSRuntimeException(new FordExceptionAttributes.Builder(CLASS_NAME, METHOD_NAME).build(), "Erro no processo de conex�o com BD", e);
        } catch (final SQLException e) {
            // Tenta duas vezes, conforme recomendacao no site da MS para tentar
            // conectar no servidor de failover
            try {

                if (this.conn == null || this.conn.isClosed()) {
                    this.conn = dataSource.getConnection(user, password);
                    this.conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                    return true;
                }

                return false;

            } catch (final SQLException ex) {
                log.log(Level.SEVERE, "Data source connection threw an error:");
                log.log(Level.SEVERE, ex.getMessage(), ex);
                throw new BARSRuntimeException(new FordExceptionAttributes.Builder(CLASS_NAME, METHOD_NAME).build(), "Erro no processo de conex�o com BD", e);
            }

        }

    }

    public void close(final boolean isToClose) {
        final String METHOD_NAME = "close";

        try {
            if (isToClose && this.conn != null && !this.conn.isClosed()) {
                this.conn.close();
                this.conn = null;
            }
        } catch (final SQLException e) {
            log.log(Level.SEVERE, "Close connection threw an error:");
            log.log(Level.SEVERE, e.getMessage(), e);
            throw new BARSRuntimeException(new FordExceptionAttributes.Builder(CLASS_NAME, METHOD_NAME).build(), "Erro no processo de conex�o com BD", e);
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public boolean beginTransaction() {
        final String METHOD_NAME = "beginTransaction";
        try {
            if (this.getConnection().getAutoCommit()) {
                this.getConnection().setAutoCommit(false);
                return true;
            }
            return false;
        } catch (final SQLException e) {
            log.log(Level.SEVERE, "Begin transaction threw an error:");
            log.log(Level.SEVERE, e.getMessage(), e);
            throw new BARSRuntimeException(new FordExceptionAttributes.Builder(CLASS_NAME, METHOD_NAME).build(), "Erro no processo de conex�o com BD", e);
        }
    }

    public void commit(final boolean isToCommit) {
        final String METHOD_NAME = "commit";
        if (isToCommit) {
            try {
                this.getConnection().commit();
                this.getConnection().setAutoCommit(true);
            } catch (final SQLException e) {
                log.log(Level.SEVERE, "Commit transaction threw an error:");
                log.log(Level.SEVERE, e.getMessage(), e);
                throw new BARSRuntimeException(new FordExceptionAttributes.Builder(CLASS_NAME, METHOD_NAME).build(), "Erro no processo de conex�o com BD", e);
            }
        }
    }

    public void rollback(final boolean isToRollback) {
        final String METHOD_NAME = "rollback";
        try {
            if (isToRollback) {
                this.getConnection().rollback();
                this.getConnection().setAutoCommit(true);
            }
        } catch (final SQLException e) {
            log.log(Level.SEVERE, "Rollback transaction threw an error:");
            log.log(Level.SEVERE, e.getMessage(), e);
            throw new BARSRuntimeException(new FordExceptionAttributes.Builder(CLASS_NAME, METHOD_NAME).build(), "Erro no processo de conex�o com BD", e);
        }
    }

}
