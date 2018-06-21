package com.lpa.business.service;

import java.util.List;
import com.ford.bars.common.service.base.ServiceBase;
import com.ford.bars.model.Banco;

public interface BancoService extends ServiceBase {
	
	Banco findByPk(int codigo);
	
	List<Banco> findByFilter(Banco banco);
	
	int insert(Banco banco);
	
	int delete(int codigo);
	 
	int update(Banco banco);
}
