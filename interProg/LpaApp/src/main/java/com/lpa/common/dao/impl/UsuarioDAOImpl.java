//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Named;

import com.lpa.common.dao.UsuarioDAO;
import com.ford.bars.common.dao.base.DAOHelper;
import com.ford.bars.common.dao.base.PrepareStatementCallbackHandler;
import com.lpa.common.dao.mapper.UsuarioMapper;
import com.ford.bars.model.Usuario;
import com.ford.it.logging.ILogger;
import com.ford.it.logging.LogFactory;

/**
 * Implementa��o para UsuarioDAO
 */
@Named
public class UsuarioDAOImpl extends DAOBaseImpl implements UsuarioDAO {

    /** Logging. */
    private static final String CLASS_NAME = UsuarioDAOImpl.class.getName();

    /** Logging. */
    private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);

    public int delete(final String cdsid) {

        final String METHOD_NAME = "delete";
        log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();
        sql.append("\n delete from ").append(Usuario.TABLE_NAME);
        sql.append("\n where ").append(Usuario.BARS04_CDSID_C).append(" = ? ");

        final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;
                DAOHelper.setParameter(statement, i++, cdsid);
            }

        });

        log.exiting(CLASS_NAME, METHOD_NAME);

        return result;

    }

    public Usuario findByCdsid(final String cdsid) {

        final String METHOD_NAME = "findByCdsid";
        log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();

        sql.append("\n select * from ").append(Usuario.TABLE_NAME);
        sql.append("\n where ").append(Usuario.BARS04_CDSID_C).append(" = ? ");

        log.exiting(CLASS_NAME, METHOD_NAME);

        return this.executeQueryObject(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;
                DAOHelper.setParameter(statement, i++, cdsid);
            }

        }, new UsuarioMapper());

    }

    @Override
    public int insert(final Usuario obj) {

        final String METHOD_NAME = "insert";

        log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();
        sql.append("\n insert into ").append(Usuario.TABLE_NAME);
        sql.append("\n (").append(Usuario.BARS04_CDSID_C);
        sql.append("\n ,").append(Usuario.BARS04_COUNTRY_C);
        sql.append("\n ,").append(Usuario.BARS04_CURRENCY_C);
        sql.append("\n ,").append(Usuario.BARS04_LANGUAGE_C);
        sql.append("\n ,").append(Usuario.BARS04_STYLE_R);
        sql.append("\n ,").append(Usuario.BARS04_TEMPLATE_X);
        sql.append("\n ,").append(Usuario.BARS04_THEME_C);
        sql.append("\n ,").append(Usuario.BARS04_CREATED_USER_C);
        sql.append("\n ,").append(Usuario.BARS04_CREATED_S);
        sql.append("\n ) values (?, ?, ?, ?, ?, ?, ?, ?, GETDATE()) ");

        final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;
                DAOHelper.setParameter(statement, i++, obj.getCdsid());
                DAOHelper.setParameter(statement, i++, obj.getPais());
                DAOHelper.setParameter(statement, i++, obj.getMoeda());
                DAOHelper.setParameter(statement, i++, obj.getIdioma());
                DAOHelper.setParameter(statement, i++, obj.getEstilo());
                DAOHelper.setParameter(statement, i++, obj.getTemplate());
                DAOHelper.setParameter(statement, i++, obj.getTema());
                DAOHelper.setParameter(statement, i++, obj.getUsuarioCriacao());
            }

        });

        log.exiting(CLASS_NAME, METHOD_NAME);

        return result;

    }

}
