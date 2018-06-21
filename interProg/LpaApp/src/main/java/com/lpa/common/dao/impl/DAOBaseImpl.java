//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import com.ford.bars.common.dao.base.CallableStatementCallbackHandler;
import com.ford.bars.common.dao.base.DAOBase;
import com.ford.bars.common.dao.base.DAOHelper;
import com.ford.bars.common.dao.base.MapperCallbackHandler;
import com.ford.bars.common.dao.base.PrepareStatementCallbackHandler;
import com.ford.bars.common.dao.base.ProcMapperCallbackHandler;
import com.ford.bars.common.dao.base.ResultSetCallbackHandler;
import com.ford.it.exception.FordExceptionAttributes;
import com.ford.it.logging.ILogger;
import com.ford.it.logging.Level;
import com.ford.it.logging.LogFactory;

/**
 * 
 * Classe para heran�a dos DAO�s
 */
public class DAOBaseImpl implements DAOBase {

    /** Logging. */
    private static final String CLASS_NAME = DAOBaseImpl.class.getName();

    /** Logging. */
    private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);

    Connection connection;

    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    protected <T> int execute(final String sql, final PrepareStatementCallbackHandler statementHandler) throws BARSRuntimeException {
        return DAOHelper.execute(this.getConnection(), sql, statementHandler);
    }

    protected <T> List<T> executeQuery(final String sql, final PrepareStatementCallbackHandler statementHandler, final MapperCallbackHandler<T> mapperHandler) {
        return DAOHelper.executeQuery(this.getConnection(), sql, statementHandler, mapperHandler);
    }

    protected <T> void executeQuery(final String sql, final PrepareStatementCallbackHandler statementHandler, final ResultSetCallbackHandler<T> resultSetHandler,
            final MapperCallbackHandler<T> mapperHandler) {

        DAOHelper.executeQuery(this.getConnection(), sql, statementHandler, resultSetHandler, mapperHandler);

    }

    protected <T> List<T> executeMultiQuery(final String sql, final PrepareStatementCallbackHandler statementHandler, final MapperCallbackHandler<T> mapperHandler) {
        return DAOHelper.executeMultiQuery(this.getConnection(), sql, statementHandler, mapperHandler);
    }

    protected <T> void executeMultiQuery(final String sql, final PrepareStatementCallbackHandler statementHandler, final ResultSetCallbackHandler<T> resultSetHandler,
            final MapperCallbackHandler<T> mapperHandler) {

        DAOHelper.executeMultiQuery(this.getConnection(), sql, statementHandler, resultSetHandler, mapperHandler);

    }

    protected <T> T executeQueryObject(final String sql, final PrepareStatementCallbackHandler statementHandler, final MapperCallbackHandler<T> mapperHandler) {

        final List<T> result = DAOHelper.executeQuery(this.getConnection(), sql, statementHandler, mapperHandler);

        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }

    }

    protected <T> List<T> executeProcedure(final String sql, final CallableStatementCallbackHandler statementHandler, final MapperCallbackHandler<T> mapperHandler,
            final ProcMapperCallbackHandler<T> procMapperHandler) {
        return DAOHelper.executeProcedure(this.getConnection(), sql, statementHandler, mapperHandler);
    }

    protected <T> T executeProcedure(final String sql, final CallableStatementCallbackHandler statementHandler, final ProcMapperCallbackHandler<T> procMapperHandler) {

        return DAOHelper.executeProcedure(this.getConnection(), sql, statementHandler, procMapperHandler);

    }

    protected <T> StringBuffer getParameterList(final List<T> list) {
        final StringBuffer sb = new StringBuffer();
        if (list != null && !list.isEmpty()) {
            sb.append(" (");
            for (int i = 0; i < list.size(); i++) {
                sb.append(i == 0 ? "?" : ", ?");
            }
            sb.append(") ");
        }
        return sb;
    }

    protected <T> StringBuffer getParameterList(final T[] list) {
        final StringBuffer sb = new StringBuffer();
        if (list != null && list.length > 0) {
            sb.append(" (");
            for (int i = 0; i < list.length; i++) {
                sb.append(i == 0 ? "?" : ", ?");
            }
            sb.append(") ");
        }
        return sb;
    }

    public String testConnection() {
        final String METHOD_NAME = "testConnection";

        try {
            final DatabaseMetaData dbMetaData = this.getConnection().getMetaData();
            if (dbMetaData != null) {
                final StringBuffer sb = new StringBuffer();
                sb.append("\nDatabase name: ");
                sb.append(dbMetaData.getDatabaseProductName());
                sb.append("\nDatabase version: ");
                sb.append(dbMetaData.getDatabaseProductVersion());
                sb.append("\nMajor version: ");
                sb.append(dbMetaData.getDatabaseMajorVersion());
                sb.append("\nMinor version: ");
                sb.append(dbMetaData.getDatabaseMinorVersion());
                return sb.toString();
            }
            return "\nDatabase doesn't support Meta Data";
        } catch (final SQLException e) {
            log.log(Level.SEVERE, "Execution of the test for database can't be completed:");
            log.log(Level.SEVERE, e.getMessage(), e);
            throw new BARSRuntimeException(new FordExceptionAttributes.Builder(CLASS_NAME, METHOD_NAME).build(), "Erro no processo de conex�o com BD", e);

        }
    }

}
