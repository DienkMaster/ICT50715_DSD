package com.lpa.business.service;

import java.util.List;
import com.ford.bars.common.service.base.ServiceBase;
import com.ford.bars.model.Processo;

public interface ProcessoService extends ServiceBase {
	
	List<Processo> findByFilter(final Processo processo);
	
	int insert(final Processo processo);

}
