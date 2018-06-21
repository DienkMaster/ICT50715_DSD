package com.lpa.common.service.impl;

import com.ford.bars.common.dao.base.ConnectionManager;
import com.ford.bars.common.dao.base.DAOBase;
import com.ford.bars.common.dao.base.DAOFactory;
import com.ford.bars.common.dao.base.IConnectionManager;
import com.lpa.common.service.Command;
import com.ford.bars.common.service.base.ServiceBase;
import com.ford.it.properties.PropertyGroup;
import com.ford.it.properties.PropertyManager;

public class ServiceBaseImpl implements ServiceBase {

    protected String jndiName;
    protected String user;
    protected String password;
    protected String schema = "";

    protected IConnectionManager connectionManager;

    public void setConnectionManager(final IConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected IConnectionManager getConnectionManager(final String sourceGroup, final String sourceUser, final String sourcePwd, final String sourceSchema) {
        if (this.connectionManager == null) {

            final PropertyManager pm = PropertyManager.getInstance();
            final PropertyGroup pg = pm.getGroup(sourceGroup);
            user = pg.getString(sourceUser);
            password = pg.getString(sourcePwd);
            if (sourceSchema != null) {
                schema = pg.getString(sourceSchema);
            }

            this.connectionManager = ConnectionManager.newInstance();

        }
        return connectionManager;
    }

    public IConnectionManager getConnectionManager() {
        return getConnectionManager("", "", "", null);
    }

    @Override
    public Boolean getConnection(final IConnectionManager connectionManager) {
        return connectionManager.open(user, password, jndiName);
    }

    protected String getJndiName() {
        return this.jndiName;
    }

    protected void setJndiName(final String jndiName) {
        this.jndiName = jndiName;
    }

    public <T> T transactionAndClose(final Command<T> command, final DAOBase dao) {

        boolean isToClose = false;
        boolean isToCommitOrRollback = false;

        try {
            // Abre conex�o com o banco
            isToClose = this.getConnectionManager().open(user, password, getJndiName());

            if (dao != null) {
                // Atribui a conex�o para o dao
                dao.setConnection(this.getConnectionManager().getConnection());
            }

            // Atribui a conex�o para a vari�vel do command
            command.setConnection(this.getConnectionManager().getConnection());

            // Inicia transa��o
            isToCommitOrRollback = this.getConnectionManager().beginTransaction();

            // Executa metodo que cont�m a l�gica da transa��o
            final T t = command.execute();

            // Efetiva transa��o
            this.getConnectionManager().commit(isToCommitOrRollback);

            return t;
        } catch (final RuntimeException e) {
            // Desfaz transa��o
            this.getConnectionManager().rollback(isToCommitOrRollback);
            throw e;
        } finally {
            // Fecha conex�o com o banco
            this.getConnectionManager().close(isToClose);
        }

    }

    public <T> T transactionAndClose(final Command<T> command) {
        return this.transactionAndClose(command, null);
    }

    public <T> T executeAndClose(final Command<T> command, final DAOBase dao) {

        boolean isToClose = false;

        try {
            // Abre conex�o com o banco
            isToClose = this.getConnectionManager().open(user, password, getJndiName());

            if (dao != null) {
                // Atribui a conex�o para o dao
                dao.setConnection(this.getConnectionManager().getConnection());
            }

            // Atribui a conex�o para a vari�vel do command
            command.setConnection(this.getConnectionManager().getConnection());

            // Executa metodo que cont�m a l�gica da transa��o
            final T t = command.execute();

            return t;
        } finally {
            // Fecha conex�o com o banco
            this.getConnectionManager().close(isToClose);
        }

    }

    public <T> T executeAndClose(final Command<T> command) {
        return this.executeAndClose(command, null);
    }

    /**
     * Efetua teste de conexao com o banco de dados
     * 
     * @param dao DAOBase
     * @return Retorna resultado do teste
     */
    public String testConnection() {
        boolean isToClose = false;

        try {
            // Abre conex�o com o banco
            isToClose = this.getConnectionManager().open(user, password, getJndiName());

            // Obtem implementacao base do dao
            final DAOBase dao = DAOFactory.instanceOf(DAOBase.class);

            if (dao != null) {
                // Atribui a conex�o para o dao
                dao.setConnection(this.getConnectionManager().getConnection());
            }

            // Efetua teste da conexao
            return dao.testConnection();
        } finally {
            // Fecha conex�o com o banco
            this.getConnectionManager().close(isToClose);
        }
    }

}
