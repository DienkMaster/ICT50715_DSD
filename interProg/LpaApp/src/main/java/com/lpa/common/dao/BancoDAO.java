package com.lpa.common.dao;

import java.util.List;

import com.ford.bars.common.dao.base.DAOBase;
import com.ford.bars.model.Banco;

/**
 * @author lmantov1
 *
 */
public interface BancoDAO extends DAOBase {
	
	/**
	 * @param codigo
	 * @return
	 */
	Banco findByPk(int codigo);
	
	/**
	 * @param banco
	 * @return
	 */
	List<Banco> findByFilter(Banco banco);
	
	/**
	 * @param banco
	 * @return
	 */
	int insert(Banco banco);
	
	/**
	 * @param banco
	 * @return
	 */
	int delete(int banco);
	
	/**
	 * @param banco
	 * @return
	 */
	int update(Banco banco);

}
