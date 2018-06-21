package com.lpa.common.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Named;

import com.lpa.common.dao.ProcessoDAO;
import com.ford.bars.common.dao.base.DAOHelper;
import com.ford.bars.common.dao.base.PrepareStatementCallbackHandler;
import com.lpa.common.dao.mapper.ProcessoMapper;
import com.ford.bars.model.Processo;
import com.ford.it.logging.ILogger;
import com.ford.it.logging.LogFactory;

@Named
public class ProcessoDAOImpl extends DAOBaseImpl implements ProcessoDAO {
	
	private static final String CLASS_NAME = ProcessoDAOImpl.class.getName();
	 private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);

	@Override
	public List<Processo> findByFilter(final Processo processo) {

		final String METHOD_NAME = "findByFilter";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		
		final StringBuffer sql = new StringBuffer();
		sql.append("\n select * from ").append(Processo.TABLE_NAME);
		sql.append("\n where 1 = 1");
		
		if(processo.getDescricao() != null && !processo.getDescricao().equals("")){
			sql.append("\n and ").append(Processo.BARS05_PROCESS_X).append(" like ? ");
		}
		
		if(processo.getStatus() > 0){
			sql.append("\n and ").append(Processo.BARS05_STATUS_R).append(" = ? ");
		}
		
		if(processo.getIniciado() != null && !processo.getIniciado().equals("")){
			sql.append("\n and ").append(Processo.BARS05_START_S).append(" between ? ");
		}
		
		if(processo.getFinalizado() != null && !processo.getFinalizado().equals("")){
			sql.append(" and ? ");
		}
		
		final List<Processo> result = this.executeQuery(sql.toString(), new PrepareStatementCallbackHandler() {
			
			@Override
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;
				
				if(processo.getDescricao() != null && !processo.getDescricao().equals("")){
					DAOHelper.setParameter(statement, i++, processo.getDescricao());
				}
				
				if(processo.getStatus() > 0){
					DAOHelper.setParameter(statement, i++, processo.getStatus());
				}
				
				if(processo.getIniciado() != null && !processo.getIniciado().equals("")){
					DAOHelper.setParameter(statement, i++, processo.getIniciado());
				}
				
				if(processo.getFinalizado() != null && !processo.getFinalizado().equals("")){
					DAOHelper.setParameter(statement, i++, processo.getFinalizado());
				}
				
			}
		}, new ProcessoMapper());
			
		log.exiting(CLASS_NAME, METHOD_NAME);
		return result;
	}

	@Override
	public int insert(final Processo processo) {

		final String METHOD_NAME = "insert";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		
		final StringBuffer sql = new StringBuffer();
		
		sql.append("\n insert into ").append(Processo.TABLE_NAME);
		sql.append("\n (").append(Processo.BARS05_COUNTRY_C);
		sql.append("\n ,").append(Processo.BARS05_PROCESS_X);
		sql.append("\n ,").append(Processo.BARS05_LOG_X);
		sql.append("\n ,").append(Processo.BARS05_ERROR_X);
		sql.append("\n ,").append(Processo.BARS05_STATUS_R);
		sql.append("\n ,").append(Processo.BARS05_START_S);
		sql.append("\n ,").append(Processo.BARS05_FINISH_S);
		sql.append("\n ) values (?, ?, ?, ?, ?, ?, GETDATE())");
		
		final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {
			
			@Override
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;
				DAOHelper.setParameter(statement, i++, processo.getPais());
				DAOHelper.setParameter(statement, i++, processo.getDescricao());
				DAOHelper.setParameter(statement, i++, processo.getLog());
				DAOHelper.setParameter(statement, i++, processo.getErro());
				DAOHelper.setParameter(statement, i++, processo.getStatus());
				DAOHelper.setParameter(statement, i++, processo.getIniciado());				
			}
		});
		
		log.exiting(CLASS_NAME, METHOD_NAME);
		return result;
	}

}
