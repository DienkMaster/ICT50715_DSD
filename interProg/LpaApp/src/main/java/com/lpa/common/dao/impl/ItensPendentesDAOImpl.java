//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Named;

import com.lpa.common.dao.ItensPendentesDAO;
import com.ford.bars.common.dao.base.DAOHelper;
import com.ford.bars.common.dao.base.PrepareStatementCallbackHandler;
import com.lpa.common.dao.mapper.ItensPendentesMapper;
import com.ford.bars.model.Extrato;
import com.ford.bars.model.FiltroItensPendentes;
import com.ford.bars.model.ItensPendentes;
import com.ford.bars.model.Razao;
import com.ford.it.logging.ILogger;
import com.ford.it.logging.LogFactory;

/**
 * Implementa��o para ItensPendentesDAO
 */
@Named
public class ItensPendentesDAOImpl extends DAOBaseImpl implements ItensPendentesDAO {

    /** Logging. */
    private static final String CLASS_NAME = ItensPendentesDAOImpl.class.getName();

    /** Logging. */
    private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);

    public int delete(final int codigo) {

        final String METHOD_NAME = "delete";
        log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();
        sql.append("\n delete from ").append(ItensPendentes.TABLE_NAME);
        sql.append("\n where ").append(ItensPendentes.BARS08_ITEM_K).append(" = ? ");

        final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;
                DAOHelper.setParameter(statement, i++, codigo);
            }

        });

        log.exiting(CLASS_NAME, METHOD_NAME);

        return result;

    }

    public ItensPendentes findByPk(final int codigo) {

        final String METHOD_NAME = "findByPk";
        log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();

        sql.append("\n select * from ").append(ItensPendentes.TABLE_NAME);
        sql.append("\n where ").append(ItensPendentes.BARS08_ITEM_K).append(" = ? ");

        log.exiting(CLASS_NAME, METHOD_NAME);

        return this.executeQueryObject(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;
                DAOHelper.setParameter(statement, i++, codigo);
            }

        }, new ItensPendentesMapper());

    }

    @Override
    public List<ItensPendentes> findByPendentes(final FiltroItensPendentes filtro) {

        final String METHOD_NAME = "findByFilter";
        log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();
        sql.append("\n SELECT JBARS08_PENDING_ITEMS.BARS08_ITEM_K, JBARS06_BANK_STATEMENTS.BARS06_BANK_STATEMENT_K, JBARS06_BANK_STATEMENTS.BARS06_HISTORY_X,");
        sql.append("\n JBARS06_BANK_STATEMENTS.BARS06_DOCUMENT_R, JBARS06_BANK_STATEMENTS.BARS06_DATE_S, JBARS06_BANK_STATEMENTS.BARS06_VALUE_A,");
        sql.append("\n JBARS06_BANK_STATEMENTS.BARS06_ACCOUNTING_TYPE_C, JBARS06_BANK_STATEMENTS.BARS06_BINDING_F,");
        sql.append("\n JBARS06_BANK_STATEMENTS.BARS06_BINDING_R , JBARS07_GENERAL_LEDGER.BARS07_LEDGER_K, JBARS07_GENERAL_LEDGER.BARS07_HISTORY_X,");
        sql.append("\n JBARS07_GENERAL_LEDGER.BARS07_DOCUMENT_R, JBARS07_GENERAL_LEDGER.BARS07_DATE_S, JBARS07_GENERAL_LEDGER.BARS07_VALUE_A,");
        sql.append("\n JBARS07_GENERAL_LEDGER.BARS07_ACCOUNTING_TYPE_C, JBARS07_GENERAL_LEDGER.BARS07_JOURNAL_ID_C,");
        sql.append("\n JBARS07_GENERAL_LEDGER.BARS07_BINDING_F, JBARS07_GENERAL_LEDGER.BARS07_BINDING_R , JBARS01_AREAS.BARS01_AREA_K,");
        sql.append("\n JBARS01_AREAS.BARS01_DESCRIPTION_X, JBARS01_AREAS.BARS01_BUSINESS_AREA_C, JBARS08_PENDING_ITEMS.BARS08_TYPE_C,");
        sql.append("\n JBARS08_PENDING_ITEMS.BARS08_RESPONSE_X, JBARS08_PENDING_ITEMS.BARS08_BINDING_F, JBARS08_PENDING_ITEMS.BARS08_CREATED_S,");
        sql.append("\n JBARS08_PENDING_ITEMS.BARS08_CREATED_USER_C, JBARS08_PENDING_ITEMS.BARS08_LAST_UPDT_S,");
        sql.append("\n JBARS08_PENDING_ITEMS.BARS08_LAST_UPDT_USER_C, JBARS08_PENDING_ITEMS.BARS08_EMAIL_S, JBARS02_BANKS.BARS02_BANK_K AS BancoExtrato, JBARS02_BANKS.BARS02_CORPORATION_C AS CorporacaoExtrato,");
        sql.append("\n JBARS02_BANKS.BARS02_ACCOUNT_C AS ContaExtrato, JBARS02_BANKS.BARS02_SUB_ACCOUNT_C AS SubContaExtrato, JBARS02_BANKS_1.BARS02_BANK_K AS BancoRazao  ,JBARS02_BANKS_1.BARS02_CORPORATION_C AS CorporacaoRazao,");
        sql.append("\n JBARS02_BANKS_1.BARS02_ACCOUNT_C AS ContaRazao, JBARS02_BANKS_1.BARS02_SUB_ACCOUNT_C AS SubContaRazao");
        sql.append("\n FROM JBARS08_PENDING_ITEMS LEFT OUTER JOIN");
        sql.append("\n JBARS02_BANKS INNER JOIN");
        sql.append("\n JBARS06_BANK_STATEMENTS ON JBARS02_BANKS.BARS02_BANK_K = JBARS06_BANK_STATEMENTS.BARS06_BARS02_BANK_K AND");
        sql.append("\n JBARS02_BANKS.BARS02_BANK_K = JBARS06_BANK_STATEMENTS.BARS06_BARS02_BANK_K ON ");
        sql.append("\n JBARS08_PENDING_ITEMS.BARS08_BARS06_BANK_STATEMENT_K = JBARS06_BANK_STATEMENTS.BARS06_BANK_STATEMENT_K LEFT OUTER JOIN");
        sql.append("\n JBARS07_GENERAL_LEDGER INNER JOIN");
        sql.append("\n JBARS02_BANKS AS JBARS02_BANKS_1 ON JBARS07_GENERAL_LEDGER.BARS07_BARS02_BANK_K = JBARS02_BANKS_1.BARS02_BANK_K ON");
        sql.append("\n JBARS08_PENDING_ITEMS.BARS08_BARS07_LEDGER_K = JBARS07_GENERAL_LEDGER.BARS07_LEDGER_K LEFT OUTER JOIN");
        sql.append("\n JBARS01_AREAS ON JBARS08_PENDING_ITEMS.BARS08_BARS01_AREA_K = JBARS01_AREAS.BARS01_AREA_K");
        sql.append("\n WHERE     (JBARS08_PENDING_ITEMS.BARS08_BINDING_F IS NULL) OR");
        sql.append("\n (JBARS08_PENDING_ITEMS.BARS08_BINDING_F = 'N') ");

        if (filtro.getTipo() != null) {
            sql.append("\n and ").append(ItensPendentes.BARS08_TYPE_C).append(" = ? ");
        }

        if (filtro.getHistorico() != null) {

            sql.append("\n and (").append(Extrato.BARS06_HISTORY_X).append(" like  ?  or ").append(Razao.BARS07_HISTORY_X).append(" like ? )");

        }

        if (filtro.getDocumento() > 0) {

            sql.append("\n and (").append(Extrato.BARS06_DOCUMENT_R).append(" like  ?  or ").append(Razao.BARS07_DOCUMENT_R).append(" like ? )");

        }

        // TODO verificar se fica assim ou usa Date
        if (filtro.getDtInicio() != null) {

            if (filtro.getDtFim() != null) {

                sql.append("\n and (").append(Extrato.BARS06_DATE_S).append(" >= ? ");
                sql.append("\n and ").append(Extrato.BARS06_DATE_S).append(" <= ? or ");

                sql.append("\n and ").append(Razao.BARS07_DATE_S).append(" >= ? ");
                sql.append("\n and ").append(Razao.BARS07_DATE_S).append(" <= ? )");

            } else {
                sql.append("\n and (").append(Extrato.BARS06_DATE_S).append(" >= ? ");
                sql.append(" or ").append(Razao.BARS07_DATE_S).append(" >= ? )");
            }

        }

        if (filtro.getValorInicio() != null && filtro.getValorInicio().doubleValue() > 0) {

            if (filtro.getValorFim() != null && filtro.getValorFim().doubleValue() > 0) {
                sql.append("\n and (").append(Extrato.BARS06_VALUE_A).append(" >= ? ");
                sql.append("\n and ").append(Extrato.BARS06_VALUE_A).append(" <= ? or ");

                sql.append("\n and ").append(Razao.BARS07_VALUE_A).append(" >= ? ");
                sql.append("\n and ").append(Razao.BARS07_VALUE_A).append(" <= ? )");

            } else {
                sql.append("\n and (").append(Extrato.BARS06_VALUE_A).append(" >= ? ");
                sql.append("\n or ").append(Razao.BARS07_VALUE_A).append(" >= ? )");

            }

        }

        if (filtro.getDebitoCredito() != null) {
            sql.append("\n and (").append(Extrato.BARS06_ACCOUNTING_TYPE_C).append(" =  ?  or ").append(Razao.BARS07_ACCOUNTING_TYPE_C).append(" like ? )");
        }

        final List<ItensPendentes> result = this.executeQuery(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;

                if (filtro.getTipo() != null) {
                    DAOHelper.setParameter(statement, i++, filtro.getTipo());
                }

                if (filtro.getHistorico() != null) {
                    DAOHelper.setParameter(statement, i++, "%" + filtro.getHistorico() + "%");
                    DAOHelper.setParameter(statement, i++, "%" + filtro.getHistorico() + "%");
                }

                if (filtro.getDocumento() > 0) {
                    DAOHelper.setParameter(statement, i++, "%" + filtro.getDocumento() + "%");
                    DAOHelper.setParameter(statement, i++, "%" + filtro.getDocumento() + "%");
                }

                // TODO verificar se fica assim ou usa Date
                if (filtro.getDtInicio() != null) {

                    if (filtro.getDtFim() != null) {

                        DAOHelper.setParameter(statement, i++, filtro.getDtInicio());
                        DAOHelper.setParameter(statement, i++, filtro.getDtFim());
                        DAOHelper.setParameter(statement, i++, filtro.getDtInicio());
                        DAOHelper.setParameter(statement, i++, filtro.getDtFim());

                    } else {
                        DAOHelper.setParameter(statement, i++, filtro.getDtInicio());
                        DAOHelper.setParameter(statement, i++, filtro.getDtInicio());

                    }

                }

                if (filtro.getValorInicio() != null && filtro.getValorInicio().doubleValue() > 0) {

                    if (filtro.getValorFim() != null && filtro.getValorFim().doubleValue() > 0) {
                        DAOHelper.setParameter(statement, i++, filtro.getValorInicio());
                        DAOHelper.setParameter(statement, i++, filtro.getValorFim());
                        DAOHelper.setParameter(statement, i++, filtro.getValorInicio());
                        DAOHelper.setParameter(statement, i++, filtro.getValorFim());

                    } else {
                        DAOHelper.setParameter(statement, i++, filtro.getValorInicio());
                        DAOHelper.setParameter(statement, i++, filtro.getValorInicio());

                    }

                }

                if (filtro.getDebitoCredito() != null) {
                    DAOHelper.setParameter(statement, i++, filtro.getDebitoCredito());
                    DAOHelper.setParameter(statement, i++, filtro.getDebitoCredito());
                }

            }

        }, new ItensPendentesMapper());

        log.exiting(CLASS_NAME, METHOD_NAME);

        return result;

    }

    @Override
    public int insert(final ItensPendentes item) {

        final String METHOD_NAME = "insert";

        log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();

        final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                final int i = 1;
            }

        });

        log.exiting(CLASS_NAME, METHOD_NAME);

        return result;

    }

    @Override
    public int updateConciliado(final ItensPendentes item) {
        final String METHOD_NAME = "update";
        log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();
        sql.append("\n update ").append(ItensPendentes.TABLE_NAME);
        sql.append("\n set ").append(ItensPendentes.BARS08_BINDING_F).append(" = 'S' ");
        sql.append("\n ,").append(ItensPendentes.BARS08_LAST_UPDT_USER_C).append(" = ? ");
        sql.append("\n ,").append(ItensPendentes.BARS08_LAST_UPDT_S).append(" = GETDATE() ");
        sql.append("\n where ").append(ItensPendentes.BARS08_ITEM_K).append(" = ? ");

        final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;

                DAOHelper.setParameter(statement, i++, item.getUsuarioAlteracao());
                DAOHelper.setParameter(statement, i++, item.getCodigo());
            }

        });
        log.exiting(CLASS_NAME, METHOD_NAME);

        return result;

    }

}
