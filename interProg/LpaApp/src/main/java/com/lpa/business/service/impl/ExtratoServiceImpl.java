package com.lpa.business.service.impl;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import com.lpa.business.service.ExtratoService;
import com.ford.bars.common.dao.ExtratoDAO;
import com.ford.bars.common.service.Command;
import com.ford.bars.common.service.impl.SqlServiceBaseImpl;
import com.ford.bars.model.Extrato;

/**
 * @author lmantov1
 *
 */
@Named
public class ExtratoServiceImpl extends SqlServiceBaseImpl implements ExtratoService, Serializable {

	@Inject
	ExtratoDAO dao;

	@Override
	public int insert(final Extrato extrato) {

		return this.executeAndClose(new Command<Integer>() {

			@Override
			public Integer execute() {
				return dao.insert(extrato);
			}
		}, dao);
	}

}
