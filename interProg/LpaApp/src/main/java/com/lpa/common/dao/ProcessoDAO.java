package com.lpa.common.dao;

import java.util.List;
import com.ford.bars.common.dao.base.DAOBase;
import com.ford.bars.model.Processo;

/**
 * @author lmantov1
 *
 */
public interface ProcessoDAO extends DAOBase {

	/**
	 * @param processo
	 * @return
	 */
	List<Processo> findByFilter(final Processo processo);
	
	/**
	 * @param processo
	 * @return
	 */
	int insert(final Processo processo);
}
