//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lpa.common.dao.base.MapperCallbackHandler;
import com.lpa.model.Stock;

public class StockMapper implements MapperCallbackHandler<Stock> {

    public Stock execute(final ResultSet rs) throws SQLException {
        final Stock bean = new Stock();
        bean.setId(rs.getString(Stock.lpa_stock_ID));
        bean.setName(rs.getString(Stock.lpa_stock_name));
        bean.setDesc(rs.getString(Stock.lpa_stock_desc));
        bean.setOnhand(rs.getString(Stock.lpa_stock_onhand));
        bean.setPrice(rs.getDouble(Stock.lpa_stock_price));
        bean.setStatus(rs.getString(Stock.lpa_stock_status));

        return bean;
    }

}
