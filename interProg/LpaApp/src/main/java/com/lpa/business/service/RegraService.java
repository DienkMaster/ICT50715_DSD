package com.lpa.business.service;

import java.util.List;

import com.ford.bars.common.service.base.ServiceBase;
import com.ford.bars.model.Regra;

public interface RegraService extends ServiceBase {
	
	Regra findByPk(final int codigo);
	
	List<Regra> findByFilter(final Regra regra);
		
	int insert(final Regra regra);
	
	int delete(final int codigo);
	
	int update(final Regra regra);
}
