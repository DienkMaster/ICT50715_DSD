package com.lpa.common.dao;

import java.util.List;

import com.lpa.common.dao.base.DAOBase;
import com.lpa.model.Stock;

/**
 * DAO para tabela JBARS01_AREAS
 */
public interface StockDAO extends DAOBase {

    Stock findByPk(final int code);

    List<Stock> findByFilter(final Stock stock);

    int insert(final Stock stock);

    int delete(final int code);

    int update(final Stock stock);

}
