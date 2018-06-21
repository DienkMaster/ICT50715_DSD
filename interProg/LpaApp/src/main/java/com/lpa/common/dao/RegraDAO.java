package com.lpa.common.dao;

import java.util.List;

import com.ford.bars.common.dao.base.DAOBase;
import com.ford.bars.model.Regra;

/**
 * @author lmantov1
 *
 */
public interface RegraDAO extends DAOBase {
	
	/**
	 * @param codigo
	 * @return
	 */
	Regra findByPk(final int codigo);
	
	/**
	 * @param regra
	 * @return
	 */
	List<Regra> findByFilter(final Regra regra);
	
	/**
	 * @param rega
	 * @return
	 */
	int insert(final Regra rega);
	
	/**
	 * @param codigo
	 * @return
	 */
	int delete(final int codigo);
	
	/**
	 * @param regra
	 * @return
	 */
	int update(final Regra regra);
	
}
