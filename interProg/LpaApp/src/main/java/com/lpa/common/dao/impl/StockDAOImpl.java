//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


import com.lpa.common.dao.StockDAO;
import com.lpa.common.dao.base.DAOHelper;
import com.lpa.common.dao.base.PrepareStatementCallbackHandler;
import com.lpa.common.dao.mapper.StockMapper;
import com.lpa.model.Stock;

//import javax.inject.Named;

//@Named
public class StockDAOImpl extends DAOBaseImpl implements StockDAO {
    private static final String CLASS_NAME = StockDAOImpl.class.getName();
    //private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);

    public int delete(final int codigo) {

        final String METHOD_NAME = "delete";
        //log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();
        sql.append("\n delete from ").append(Stock.lpa_table_name);
        sql.append("\n where ").append(Stock.lpa_stock_ID).append(" = ? ");

        final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;
                DAOHelper.setParameter(statement, i++, codigo);
            }

        });

        //log.exiting(CLASS_NAME, METHOD_NAME);
        return result;
    }

    public Stock findByPk(final int code) {

        final String METHOD_NAME = "findByPk";
        //log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();

        sql.append("\n select * from ").append(Stock.lpa_table_name);
        sql.append("\n where ").append(Stock.lpa_stock_ID).append(" = ? ");

        //log.exiting(CLASS_NAME, METHOD_NAME);

        return this.executeQueryObject(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;
                DAOHelper.setParameter(statement, i++, code);
            }

        }, new StockMapper());

    }

    @Override
    public List<Stock> findByFilter(final Stock stock) {
        final String METHOD_NAME = "findByFilter";
        //log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();

        sql.append("\n select * from ").append(Stock.lpa_table_name);
        sql.append("\n where 1 = 1");

        if (stock.getName() != null && !stock.getName().equals("")) {
            sql.append("\n and ").append(Stock.lpa_table_name).append(" = ? ");
        }

        if (stock.getDesc() != null && !stock.getDesc().equals("")) {
            sql.append("\n and ").append(Stock.lpa_stock_desc).append(" = ? ");
        }

        if (stock.getOnhand() != null && !stock.getOnhand().equals("")) {
            sql.append("\n and ").append(Stock.lpa_stock_onhand).append(" = ? ");
        }

        if (stock.getPrice() > 0) {
            sql.append("\n and ").append(Stock.lpa_stock_price).append(" = ? ");
        }

        if (stock.getStatus() != null && !stock.getStatus().equals("")) {
            sql.append("\n and ").append(Stock.lpa_stock_status).append(" = ? ");
        }

        final List<Stock> result = this.executeQuery(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;

                if (stock.getName() != null && !stock.getName().equals("")) {
                    DAOHelper.setParameter(statement, i++, stock.getName());
                }

                if (stock.getDesc() != null && !stock.getDesc().equals("")) {
                    DAOHelper.setParameter(statement, i++, stock.getDesc());
                }

                if (stock.getOnhand() != null && !stock.getOnhand().equals("")) {
                    DAOHelper.setParameter(statement, i++, stock.getOnhand());
                }

                if (stock.getPrice() > 0) {
                    DAOHelper.setParameter(statement, i++, stock.getPrice());
                }

                if (stock.getStatus() != null && !stock.getStatus().equals("")) {
                    DAOHelper.setParameter(statement, i++, stock.getStatus());
                }
            }

        }, new StockMapper());

        //log.exiting(CLASS_NAME, METHOD_NAME);
        return result;
    }

    @Override
    public int insert(final Area area) {

        final String METHOD_NAME = "insert";

        log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();
        sql.append("\n insert into ").append(Area.TABLE_NAME);
        sql.append("\n (").append(Area.BARS01_BUSINESS_AREA_C);
        sql.append("\n ,").append(Area.BARS01_CDSID_C);
        sql.append("\n ,").append(Area.BARS01_COUNTRY_C);
        sql.append("\n ,").append(Area.BARS01_DESCRIPTION_X);
        sql.append("\n ,").append(Area.BARS01_CREATED_USER_C);
        sql.append("\n ,").append(Area.BARS01_STATUS_R);
        sql.append("\n ,").append(Area.BARS01_CREATED_S);
        sql.append("\n ) values (?, ?, ?, ?, ?, ?, GETDATE()) ");

        final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;
                DAOHelper.setParameter(statement, i++, area.getArea());
                DAOHelper.setParameter(statement, i++, area.getCdsid());
                DAOHelper.setParameter(statement, i++, area.getPais());
                DAOHelper.setParameter(statement, i++, area.getDescricao());
                DAOHelper.setParameter(statement, i++, area.getUsuarioCriacao());
                DAOHelper.setParameter(statement, i++, area.getStatus());
            }

        });

        log.exiting(CLASS_NAME, METHOD_NAME);

        return result;

    }

    @Override
    public int update(final Area area) {
        final String METHOD_NAME = "update";
        log.entering(CLASS_NAME, METHOD_NAME);

        final StringBuffer sql = new StringBuffer();
        sql.append("\n update ").append(Area.TABLE_NAME);
        sql.append("\n set ").append(Area.BARS01_BUSINESS_AREA_C).append(" = ? ");
        sql.append("\n ,").append(Area.BARS01_CDSID_C).append(" = ? ");
        sql.append("\n ,").append(Area.BARS01_COUNTRY_C).append(" = ? ");
        sql.append("\n ,").append(Area.BARS01_DESCRIPTION_X).append(" = ? ");
        sql.append("\n ,").append(Area.BARS01_STATUS_R).append(" = ? ");
        sql.append("\n ,").append(Area.BARS01_LAST_UPDT_USER_C).append(" = ? ");
        sql.append("\n ,").append(Area.BARS01_LAST_UPDT_S).append(" = GETDATE() ");
        sql.append("\n where ").append(Area.BARS01_AREA_K).append(" = ? ");

        final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {

            public void execute(final PreparedStatement statement) throws SQLException {
                int i = 1;

                DAOHelper.setParameter(statement, i++, area.getArea());
                DAOHelper.setParameter(statement, i++, area.getCdsid());
                DAOHelper.setParameter(statement, i++, area.getPais());
                DAOHelper.setParameter(statement, i++, area.getDescricao());
                DAOHelper.setParameter(statement, i++, area.getStatus());
                DAOHelper.setParameter(statement, i++, area.getUsuarioAlteracao());
                DAOHelper.setParameter(statement, i++, area.getCod());
            }

        });
        log.exiting(CLASS_NAME, METHOD_NAME);

        return result;

    }

}
