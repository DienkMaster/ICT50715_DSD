package com.lpa.common.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Named;
import com.lpa.common.dao.RegraDAO;
import com.ford.bars.common.dao.base.DAOHelper;
import com.ford.bars.common.dao.base.PrepareStatementCallbackHandler;
import com.lpa.common.dao.mapper.RegraMapper;
import com.ford.bars.model.Regra;
import com.ford.it.logging.ILogger;
import com.ford.it.logging.LogFactory;

@Named
public class RegraDAOImpl extends DAOBaseImpl implements RegraDAO {
	
	private static final String CLASS_NAME = RegraDAOImpl.class.getName();	
	private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);

	@Override
	public Regra findByPk(final int codigo) {

		final String METHOD_NAME = "findByPk";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		StringBuffer sql = new StringBuffer();		
		sql.append("\n select * from ").append(Regra.TABLE_NAME);
		sql.append("\n where ").append(Regra.BARS03_RULES_K).append(" = ? ");
		
		log.exiting(CLASS_NAME, METHOD_NAME);
		
		return this.executeQueryObject(sql.toString(), new PrepareStatementCallbackHandler() {
						
			public void execute(final PreparedStatement statement) throws SQLException {
				int i = 0;
				DAOHelper.setParameter(statement, i++, codigo);				
			}
		}, new RegraMapper());		
	}

	@Override
	public List<Regra> findByFilter(final Regra regra) {

		final String METHOD_NAME = "findByFilter";
        log.entering(CLASS_NAME, METHOD_NAME); 
		
        final StringBuffer sql = new StringBuffer();
        
        sql.append("\n select * from ").append(Regra.TABLE_NAME);
        sql.append("\n where 1 = 1 ");
        
        if(regra.getNomeRegra() != null && !regra.getNomeRegra().equals("") ){
        	sql.append("\n and ").append(Regra.BARS03_REMARKS_X).append(" = ? ");
        }	
        
        if(regra.getBanco() > 0){
        	sql.append("\n and ").append(Regra.BARS03_BARS02_BANK_K).append(" = ? ");
        }
		
        if(regra.getStatus() > 0){
        	sql.append("\n and ").append(Regra.BARS03_STATUS_R).append(" = ? ");
        }
		
        final List<Regra> result = this.executeQuery(sql.toString(), new PrepareStatementCallbackHandler() {
			
			@Override
			public void execute(final PreparedStatement statement) throws SQLException {
				int i = 1;
				
				if(regra.getNomeRegra() != null && !regra.getNomeRegra().equals("") ){
		        	DAOHelper.setParameter(statement, i++, regra.getNomeRegra());
		        }	
		        
		        if(regra.getBanco() > 0){
		        	DAOHelper.setParameter(statement, i++, regra.getBanco());
		        }
				
		        if(regra.getStatus() > 0){
		        	DAOHelper.setParameter(statement, i++, regra.getStatus());
		        }			
				
			}
		}, new RegraMapper());
		
		log.exiting(CLASS_NAME, METHOD_NAME);        
		return result;
	}

	@Override
	public int insert(final Regra regra) {
		
		final String METHOD_NAME = "insert";
        log.entering(CLASS_NAME, METHOD_NAME);

		final StringBuffer sql = new StringBuffer();
		sql.append("\n insert into ").append(Regra.TABLE_NAME);
		sql.append("\n (").append(Regra.BARS03_BARS02_BANK_K);
		sql.append("\n ,").append(Regra.BARS03_VALUE_F);
		sql.append("\n ,").append(Regra.BARS03_TRANSACTION_F);
		sql.append("\n ,").append(Regra.BARS03_DOCUMENT_F);
		sql.append("\n ,").append(Regra.BARS03_DATE_F);
		sql.append("\n ,").append(Regra.BARS03_PARAMETER1_F);
		sql.append("\n ,").append(Regra.BARS03_PARAMETER2_F);
		sql.append("\n ,").append(Regra.BARS03_PARAMETER3_F);
		sql.append("\n ,").append(Regra.BARS03_PARAMETER4_F);
		sql.append("\n ,").append(Regra.BARS03_PARAMETER5_F);
		sql.append("\n ,").append(Regra.BARS03_REMARKS_X);
		sql.append("\n ,").append(Regra.BARS03_BIND_TYPE_R);
		sql.append("\n ,").append(Regra.BARS03_STATUS_R);
		sql.append("\n ,").append(Regra.BARS03_CREATED_USER_C);
		sql.append("\n ,").append(Regra.BARS03_CREATED_S);
		sql.append("\n ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE()) ");
	
		final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {
			
			@Override
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;
				DAOHelper.setParameter(statement, i++, regra.getBanco());
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroValor()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroTransacao()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroDocumento()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroData()));		
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroUm()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroDois()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroTres()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroQuatro()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroCinco()));
				DAOHelper.setParameter(statement, i++, regra.getNomeRegra());
				DAOHelper.setParameter(statement, i++, regra.getAprovacao());
				DAOHelper.setParameter(statement, i++, regra.getStatus());
				DAOHelper.setParameter(statement, i++, regra.getUsuarioCriacao());				
			}
		});
		
		log.exiting(CLASS_NAME, METHOD_NAME);		
		return result;
	}
	
	public String chekarParametro(boolean parametro){
		if(parametro){
			return "S";
		}else{
			return "N";
		} 
	}
	

	@Override
	public int delete(final int codigo) {

		final String METHOD_NAME = "delete";
        log.entering(CLASS_NAME, METHOD_NAME);
		
        final StringBuffer sql = new StringBuffer();
        sql.append("\n delete from ").append(Regra.TABLE_NAME);
        sql.append("\n where ").append(Regra.BARS03_RULES_K).append(" = ? ");
        
        final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {
						
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;
				DAOHelper.setParameter(statement, i++, codigo);				
			}
		});
		
        log.exiting(CLASS_NAME, METHOD_NAME);		
		return result;
	}

	@Override
	public int update(final Regra regra) {
		
		final String METHOD_NAME = "update";
        log.entering(CLASS_NAME, METHOD_NAME);

		final StringBuffer sql = new StringBuffer();
		sql.append("\n update ").append(Regra.TABLE_NAME);
		sql.append("\n set ").append(Regra.BARS03_BARS02_BANK_K).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_VALUE_F).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_TRANSACTION_F).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_DOCUMENT_F).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_DATE_F).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_PARAMETER1_F).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_PARAMETER2_F).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_PARAMETER3_F).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_PARAMETER4_F).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_PARAMETER5_F).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_REMARKS_X).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_BIND_TYPE_R).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_STATUS_R).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_LAST_UPDT_USER_C).append(" = ? ");
		sql.append("\n ,").append(Regra.BARS03_LAST_UPDT_S).append(" = GETDATE() ");
		sql.append("\n where ").append(Regra.BARS03_RULES_K).append(" = ? ");
	
		final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {
			
			@Override
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;
				DAOHelper.setParameter(statement, i++, regra.getBanco());
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroValor()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroTransacao()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroDocumento()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroData()));		
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroUm()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroDois()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroTres()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroQuatro()));
				DAOHelper.setParameter(statement, i++, chekarParametro(regra.isParametroCinco()));
				DAOHelper.setParameter(statement, i++, regra.getNomeRegra());
				DAOHelper.setParameter(statement, i++, regra.getAprovacao());
				DAOHelper.setParameter(statement, i++, regra.getStatus());
				DAOHelper.setParameter(statement, i++, regra.getUsuarioCriacao());
				DAOHelper.setParameter(statement, i++, regra.getCod());				
			}
		});
		
		log.exiting(CLASS_NAME, METHOD_NAME);		
		return result;		
		
	}

}
