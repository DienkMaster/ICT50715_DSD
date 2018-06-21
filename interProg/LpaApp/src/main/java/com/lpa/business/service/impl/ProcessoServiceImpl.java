package com.lpa.business.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.lpa.business.service.ProcessoService;
import com.ford.bars.common.dao.ProcessoDAO;
import com.ford.bars.common.layer.exception.BARSRuntimeException;
import com.ford.bars.common.service.Command;
import com.ford.bars.common.service.impl.SqlServiceBaseImpl;
import com.ford.bars.model.Processo;

@Named
public class ProcessoServiceImpl extends SqlServiceBaseImpl implements
		ProcessoService, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	ProcessoDAO dao;

	@Override
	public List<Processo> findByFilter(final Processo processo) {

		final Command<List<Processo>> command = new Command<List<Processo>>() {

			@Override
			public List<Processo> execute() throws BARSRuntimeException {
				return dao.findByFilter(processo);
			}
		};

		return this.executeAndClose(command, dao);
	}

	@Override
	public int insert(final Processo processo) {

		return this.executeAndClose(new Command<Integer>() {
			@Override
			public Integer execute() {
				return dao.insert(processo);
			}
		}, dao);
	}

}
