package com.lpa.business.service.impl;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import com.lpa.business.service.BancoService;
import com.ford.bars.common.dao.BancoDAO;
import com.ford.bars.common.layer.exception.BARSRuntimeException;
import com.ford.bars.common.service.Command;
import com.ford.bars.common.service.impl.SqlServiceBaseImpl;
import com.ford.bars.model.Banco;
import com.sun.istack.internal.FinalArrayList;

@Named
public class BancoServiceImpl extends SqlServiceBaseImpl implements BancoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	BancoDAO dao;

	@Override
	public Banco findByPk(final int codigo) {
		Command<Banco> command = new Command<Banco>() {
			@Override
			public Banco execute() {
				return dao.findByPk(codigo);
			}
		};
		return this.executeAndClose(command, dao);
	}

	@Override
	public List<Banco> findByFilter(final Banco banco) {
		Command<List<Banco>> command = new Command<List<Banco>>() {
			@Override
			public List<Banco> execute() {
				return dao.findByFilter(banco);
			}
		};
		return this.executeAndClose(command, dao);
	}

	@Override
	public int insert(final Banco banco) {
			
		return this.executeAndClose(new Command<Integer>() {
			@Override
			public Integer execute(){
				return dao.insert(banco);
			}
		}, dao);
	}

	@Override
	public int delete(final int codigo) {
		Command<Integer> command = new Command<Integer>() {
			@Override
			public Integer execute(){
				return dao.delete(codigo);
			}
		};		
		return this.executeAndClose(command, dao);
	}

	@Override
	public int update(final Banco banco) {

		Command<Integer> command = new Command<Integer>() {
			
			@Override
			public Integer execute() throws BARSRuntimeException {
				dao.setConnection(this.getConnection());
				return dao.update(banco);
			}
		};
		
		return this.executeAndClose(command);
	}

}
