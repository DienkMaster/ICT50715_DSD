package com.lpa.business.service.impl;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.lpa.business.service.RegraService;
import com.ford.bars.common.dao.RegraDAO;
import com.ford.bars.common.layer.exception.BARSRuntimeException;
import com.ford.bars.common.service.Command;
import com.ford.bars.common.service.impl.SqlServiceBaseImpl;
import com.ford.bars.model.Regra;

@Named
public class RegraServiceImpl extends SqlServiceBaseImpl implements RegraService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	RegraDAO dao;

	@Override
	public Regra findByPk(final int codigo) {
		
		Command<Regra> command = new Command<Regra>() {
			
			@Override
			public Regra execute() throws BARSRuntimeException {
	
				return dao.findByPk(codigo);
			}
		};		
		return this.executeAndClose(command, dao);
	}

	@Override
	public List<Regra> findByFilter(final Regra regra) {
		
		Command<List<Regra>> command = new Command<List<Regra>>() {
			
			@Override
			public List<Regra> execute() throws BARSRuntimeException {
				return dao.findByFilter(regra);
			}
		};
		
		return this.executeAndClose(command, dao);
	}


	public int insert(final Regra regra) {

		return this.executeAndClose(new Command<Integer>() {
			@Override
			public Integer execute() {
				return dao.insert(regra);
			}
		}, dao);
	}

	@Override
	public int delete(final int codigo) {
		
		Command<Integer> command = new Command<Integer>() {
			
			@Override
			public Integer execute() throws BARSRuntimeException {				
				return dao.delete(codigo);
			}
		};	
		
		return this.executeAndClose(command, dao);
	}

	@Override
	public int update(final Regra regra) {
		
		Command<Integer> command = new Command<Integer>() {

			@Override
			public Integer execute() throws BARSRuntimeException {
				dao.setConnection(this.getConnection());
				return dao.update(regra);
			}
		};
		return this.executeAndClose(command);

	}

}
