package com.lpa.common.dao.base;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.lpa.common.layer.exception.LpaBaseRuntimeException;

public class DAOHelper {
    private static final String CLASS_NAME = ConnectionManager.class.getName();
    private static final int SQL_ERROR_DUPLICATE_KEY = 2627;
    private static final int SQL_ERROR_FOREIGN_KEY = 547;
    private static final int SQL_ERROR_TRUNCATE = 8152;
    private static final int SQL_ERROR_COLUMN_IS_NULL = 515;

    public static <T> List<T> executeQuery(final Connection conn, final String sql,
                                           final PrepareStatementCallbackHandler statementHandler,
                                           final MapperCallbackHandler<T> mapperHandler) throws LpaBaseRuntimeException{
        final ResultSetCallbackHandler<T> resultSetHandler = new ResultSetCallbackHandler<T>() {
            final List<T> result = new LinkedList<T>();

            public void execute(final T bean) {
                this.result.add(bean);
            }
            public List<T> getList() {
                return this.result;
            }
        };

        DAOHelper.executeQuery(conn, sql, statementHandler, resultSetHandler, mapperHandler);
        return resultSetHandler.getList();
    }

    public static <T> void executeQuery(final Connection conn, final String sql, final PrepareStatementCallbackHandler statementHandler,
                                        final ResultSetCallbackHandler<T> resultSetHandler, final MapperCallbackHandler<T> mapperHandler) throws LpaBaseRuntimeException{
        final String METHOD_NAME = "executeQuery";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = conn.prepareStatement(sql.toString());
            statementHandler.execute(statement);
            rs = statement.executeQuery();
            while (rs.next()) {
                resultSetHandler.execute(mapperHandler.execute(rs));
            }
        } catch (final Exception e) {
            throw new LpaBaseRuntimeException(String.format("%s: %s - Data base execution error", CLASS_NAME, METHOD_NAME), e.getCause());
        } finally {
            close(rs);
            close(statement);
        }
    }

    public static <T> List<T> executeMultiQuery(final Connection conn, final String sql, final PrepareStatementCallbackHandler statementHandler,
                                                final MapperCallbackHandler<T> mapperHandler) throws LpaBaseRuntimeException{
        final ResultSetCallbackHandler<T> resultSetHandler = new ResultSetCallbackHandler<T>() {
            final List<T> result = new LinkedList<T>();
            public void execute(final T bean) {
                this.result.add(bean);
            }
            public List<T> getList() {
                return this.result;
            }
        };

        DAOHelper.executeMultiQuery(conn, sql, statementHandler, resultSetHandler, mapperHandler);
        return resultSetHandler.getList();
    }

    public static <T> void executeMultiQuery(final Connection conn, final String sql, final PrepareStatementCallbackHandler statementHandler,
                                             final ResultSetCallbackHandler<T> resultSetHandler, final MapperCallbackHandler<T> mapperHandler) throws LpaBaseRuntimeException{
        final String METHOD_NAME = "executeMultiQuery";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = conn.prepareStatement(sql.toString());
            statementHandler.execute(statement);
            boolean result = statement.execute();
            while (!result) {
                if (statement.getUpdateCount() == 0) {
                    // End of results.
                    break;
                }
                result = statement.getMoreResults();
            }
            rs = statement.getResultSet();
            while (rs != null && rs.next()) {
                resultSetHandler.execute(mapperHandler.execute(rs));
            }
        } catch (final Exception e) {
            throw new LpaBaseRuntimeException(String.format("%s: %s - Data base execution error", CLASS_NAME, METHOD_NAME), e.getCause());
        } finally {
            close(rs);
            close(statement);
        }

    }

    public static <T> int execute(final Connection conn, final String sql, final PrepareStatementCallbackHandler statementHandler) throws LpaBaseRuntimeException {

        PreparedStatement statement = null;
        final String METHOD_NAME = "execute";

        try {
            statement = conn.prepareStatement(sql.toString());
            statementHandler.execute(statement);
            return statement.executeUpdate();
        } catch (final Exception e) {
            String msgErro = null;
            //log.log(Level.SEVERE, "Execution of the sql statement has an error:");
            //log.log(Level.SEVERE, e.getMessage(), e);
            if (e instanceof SQLException) {
                if (((SQLException)e).getErrorCode() == DAOHelper.SQL_ERROR_DUPLICATE_KEY) {
                    msgErro = "Erro duplicate key";
                }
                if (((SQLException)e).getErrorCode() == DAOHelper.SQL_ERROR_FOREIGN_KEY) {
                    if (sql.toUpperCase().indexOf("DELETE") >= 0) {
                        msgErro = "Erro foreign key delete";
                    } else {
                        msgErro = "Erro foreign key";
                    }
                }
                if (((SQLException)e).getErrorCode() == DAOHelper.SQL_ERROR_TRUNCATE) {
                    msgErro = "Erro truncate";
                }
                if (((SQLException)e).getErrorCode() == DAOHelper.SQL_ERROR_COLUMN_IS_NULL) {
                    msgErro = "Erro column is null";
                }
            }
            if (msgErro == null) {
                throw new LpaBaseRuntimeException(String.format("%s: %s - Data base execution error", CLASS_NAME, METHOD_NAME), e.getCause());
            } else {
                throw new LpaBaseRuntimeException(String.format("%s: %s - %s", CLASS_NAME, METHOD_NAME, msgErro), e.getCause());
            }

        } finally {
            close(statement);
        }

    }

    public static <T> List<T> executeProcedure(final Connection conn, final String sql, final CallableStatementCallbackHandler statementHandler, final MapperCallbackHandler<T> mapperHandler) {

        final ResultSetCallbackHandler<T> resultSetHandler = new ResultSetCallbackHandler<T>() {

            final List<T> result = new LinkedList<T>();

            public void execute(final T bean) {
                this.result.add(bean);
            }

            public List<T> getList() {
                return this.result;
            }
        };

        DAOHelper.executeProcedure(conn, sql, statementHandler, resultSetHandler, mapperHandler);

        return resultSetHandler.getList();

    }

    public static <T> T executeProcedure(final Connection conn, final String sql, final CallableStatementCallbackHandler statementHandler, final ProcMapperCallbackHandler<T> procMapperHandler) {

        final String METHOD_NAME = "executeProcedure";

        CallableStatement statement = null;
        final ResultSet rs = null;
        T t;

        try {
            statement = conn.prepareCall(sql.toString());
            statementHandler.execute(statement);
            statement.execute();
            t = procMapperHandler.execute(statement);
        } catch (final Exception e) {
            //log.log(Level.SEVERE, "Execution of the sql statement has an error:");
            //log.log(Level.SEVERE, e.getMessage(), e);
            throw new BARSRuntimeException(new FordExceptionAttributes.Builder(CLASS_NAME, METHOD_NAME).build(), "Erro executando procedure", e);
        } finally {
            close(rs);
            close(statement);
        }

        return t;
    }

    public static <T> List<T> executeProcedure(final Connection conn, final String sql, final CallableStatementCallbackHandler statementHandler, final ResultSetCallbackHandler<T> resultSetHandler,
            final MapperCallbackHandler<T> mapperHandler) {

        final String METHOD_NAME = "executeProcedure";

        CallableStatement statement = null;
        ResultSet rs = null;
        final List<T> returnList = new ArrayList<T>();

        try {
            statement = conn.prepareCall(sql.toString());
            statementHandler.execute(statement);
            boolean results = statement.execute();
            do {
                if (results) {
                    rs = statement.getResultSet();
                    while (rs.next()) {
                        resultSetHandler.execute(mapperHandler.execute(rs));
                    }
                }
                results = statement.getMoreResults();
            } while (results);
        } catch (final Exception e) {
            //log.log(Level.SEVERE, "Execution of the sql statement has an error:");
            //log.log(Level.SEVERE, e.getMessage(), e);
            throw new BARSRuntimeException(new FordExceptionAttributes.Builder(CLASS_NAME, METHOD_NAME).build(), "Erro executando procedure", e);
        } finally {
            close(rs);
            close(statement);
        }

        return returnList;
    }

    private static void close(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (final SQLException e) {
                //log.log(Level.SEVERE, "RecordSet close threw an error:");
                //log.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    private static void close(final PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (final SQLException e) {
                //log.log(Level.SEVERE, "Statement close threw an error:");
                //log.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public static java.sql.Date getDateSql(final Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    public static java.sql.Time getTimeSql(final Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Time(date.getTime());
    }

    public static java.sql.Timestamp getTimeStampSql(final Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Timestamp(date.getTime());
    }

    public static void setParameter(final PreparedStatement statement, final int i, final Long value) throws SQLException {
        if (value != null) {
            statement.setLong(i, value);
        } else {
            statement.setNull(i, Types.NUMERIC);
        }
    }

    public static void setParameter(final PreparedStatement statement, final int i, final Integer value) throws SQLException {
        if (value != null) {
            statement.setInt(i, value);
        } else {
            statement.setNull(i, Types.NUMERIC);
        }
    }

    public static void setParameter(final PreparedStatement statement, final int i, final Short value) throws SQLException {
        if (value != null) {
            statement.setShort(i, value);
        } else {
            statement.setNull(i, Types.NUMERIC);
        }
    }

    public static void setParameter(final PreparedStatement statement, final int i, final Byte value) throws SQLException {
        if (value != null) {
            statement.setShort(i, value);
        } else {
            statement.setNull(i, Types.NUMERIC);
        }
    }

    public static void setParameter(final PreparedStatement statement, final int i, final String value) throws SQLException {
        if (value != null) {
            statement.setString(i, value);
        } else {
            statement.setNull(i, Types.VARCHAR);
        }
    }

    public static void setParameter(final PreparedStatement statement, final int i, final Date value) throws SQLException {
        if (value != null) {
            statement.setDate(i, getDateSql(value));
        } else {
            statement.setNull(i, Types.DATE);
        }
    }

    public static void setParameterTimestamp(final PreparedStatement statement, final int i, final Date value) throws SQLException {
        if (value != null) {
            statement.setTimestamp(i, getTimeStampSql(value));
        } else {
            statement.setNull(i, Types.TIMESTAMP);
        }
    }

    public static void setParameterTime(final PreparedStatement statement, final int i, final Date value) throws SQLException {
        if (value != null) {
            statement.setTime(i, getTimeSql(value));
        } else {
            statement.setNull(i, Types.TIME);
        }
    }

    public static void setParameter(final PreparedStatement statement, final int i, final BigDecimal value) throws SQLException {
        if (value != null) {
            statement.setBigDecimal(i, value);
        } else {
            statement.setNull(i, Types.NUMERIC);
        }
    }

    public static void setParameter(final PreparedStatement statement, final int i, final byte[] value) throws SQLException {
        if (value != null) {
            statement.setBytes(i, value);
        } else {
            statement.setNull(i, Types.VARBINARY);
        }
    }

    public static boolean isFilled(final Integer value) {
        return value != null;
    }

    public static boolean isFilled(final String value) {
        return value != null && !value.equals("");
    }    
      
}
