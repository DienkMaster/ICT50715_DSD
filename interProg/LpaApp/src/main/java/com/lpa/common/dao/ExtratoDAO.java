package com.lpa.common.dao;

import java.util.List;

import com.ford.bars.common.dao.base.DAOBase;
import com.ford.bars.model.Extrato;

/**
 * @author lmantov1
 *
 */
public interface ExtratoDAO extends DAOBase {
	
	/**
	 * @param extrato
	 * @return
	 */
	int insert(final Extrato extrato);

}
