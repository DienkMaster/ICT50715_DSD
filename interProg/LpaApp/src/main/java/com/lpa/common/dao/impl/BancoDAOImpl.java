package com.lpa.common.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Named;

import com.lpa.common.dao.BancoDAO;
import com.ford.bars.common.dao.base.DAOHelper;
import com.ford.bars.common.dao.base.PrepareStatementCallbackHandler;
import com.lpa.common.dao.mapper.BancoMapper;
import com.ford.bars.model.Banco;
import com.ford.it.logging.ILogger;
import com.ford.it.logging.LogFactory;

/**
 * @author lmantov1
 *
 */
@Named
public class BancoDAOImpl extends DAOBaseImpl implements BancoDAO {
	
	private static final String CLASS_NAME = BancoDAOImpl.class.getName();
	
	private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);
			

	@Override
	public Banco findByPk(final int codigo) {

		final String METHOD_NAME = "findByPk";
		log.entering(CLASS_NAME, METHOD_NAME);

		final StringBuffer sql = new StringBuffer();
		
		sql.append("\n select * from ").append(Banco.TABLE_NAME);
		sql.append("\n where ").append(Banco.BARS02_BANK_K).append(" = ? ");
		
		log.exiting(CLASS_NAME, METHOD_NAME);

		return this.executeQueryObject(sql.toString(), new PrepareStatementCallbackHandler() {
			
			@Override
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;
				DAOHelper.setParameter(statement, i++, codigo);
				
			}
		}, new BancoMapper());
	}

	@Override
	public List<Banco> findByFilter(final Banco banco) {

		final String METHOD_NAME = "findByFilter";
        log.entering(CLASS_NAME, METHOD_NAME);
        
        StringBuffer sql = new StringBuffer();
        
        sql.append("\n select * from ").append(Banco.TABLE_NAME);
        //TODO remover .....
        sql.append("\n where 1 = 1");
		
		if(banco.getBanco() > 0){
			sql.append("\n and ").append(Banco.BARS02_BANK_C).append(" = ?");
		}
		
		if(banco.getCorporacao() > 0 ){
			sql.append("\n and ").append(Banco.BARS02_CORPORATION_C).append(" = ?");
		}
		
		if(banco.getConta() != null && !banco.getConta().equals("")){
			sql.append("\n and ").append(Banco.BARS02_BANK_ACCOUNT_C).append(" = ?");
		}
        
		if(banco.getSubConta() != null && !banco.getSubConta().equals("")){
			sql.append("\n and ").append(Banco.BARS02_SUB_ACCOUNT_C).append(" = ?");
		}
		
		List<Banco> result = this.executeQuery(sql.toString(), new PrepareStatementCallbackHandler() {
			
			
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;
				
				if(banco.getBanco() > 0){
					DAOHelper.setParameter(statement, i++, banco.getBanco());
				}
				
				if(banco.getCorporacao() > 0 ){
					DAOHelper.setParameter(statement, i++, banco.getCorporacao());
				}
				
				if(banco.getConta() != null && !banco.getConta().equals("")){
					DAOHelper.setParameter(statement, i++, banco.getConta());
				}
		        
				if(banco.getSubConta() != null && !banco.getSubConta().equals("")){
					DAOHelper.setParameter(statement, i++, banco.getSubConta());
				}				
				
			}
		}, new BancoMapper());
		
		log.exiting(CLASS_NAME, METHOD_NAME);
				
		return result;
	}

	@Override
	public int insert(final Banco banco) {
		
		//TODO campo que ainda precisa ser criado no banco
		//sql.append("\n ,").append(Banco.BARS02_OPLOC_R);	
		//DAOHelper.setParameter(statement, i++, banco.getCentroCusto());	

		String METHOD_NAME = "insert";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		StringBuffer sql = new StringBuffer();
		sql.append("\n insert into ").append(Banco.TABLE_NAME);
		sql.append("\n (").append(Banco.BARS02_COUNTRY_C);
		sql.append("\n ,").append(Banco.BARS02_BANK_C);
		sql.append("\n ,").append(Banco.BARS02_BANK_AGENCY_C);
		sql.append("\n ,").append(Banco.BARS02_BANK_ACCOUNT_C);
		sql.append("\n ,").append(Banco.BARS02_DESCRIPTION_X);
		sql.append("\n ,").append(Banco.BARS02_CORPORATION_C);
		sql.append("\n ,").append(Banco.BARS02_ACCOUNT_C);
		sql.append("\n ,").append(Banco.BARS02_SUB_ACCOUNT_C);
		sql.append("\n ,").append(Banco.BARS02_STATUS_R);			
		sql.append("\n ,").append(Banco.BARS02_CREATED_USER_C);
		sql.append("\n ,").append(Banco.BARS02_CREATED_S);
		sql.append("\n ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE()) ");
		
		int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {
						
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;				
				DAOHelper.setParameter(statement, i++, banco.getPais());
				DAOHelper.setParameter(statement, i++, banco.getBanco());
				DAOHelper.setParameter(statement, i++, banco.getAgencia());
				DAOHelper.setParameter(statement, i++, banco.getContaCorrente());
				DAOHelper.setParameter(statement, i++, banco.getDescricao());
				DAOHelper.setParameter(statement, i++, banco.getCorporacao());			
				DAOHelper.setParameter(statement, i++, banco.getConta());			
				DAOHelper.setParameter(statement, i++, banco.getSubConta());
				DAOHelper.setParameter(statement, i++, banco.getStatus());						
				DAOHelper.setParameter(statement, i++, banco.getUsuarioCriacao());
			}
		});
		
		log.exiting(CLASS_NAME, METHOD_NAME);
		
		return result;
	}

	@Override
	public int delete(final int codigo) {
		
		String METHOD_NAME = "delete";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		StringBuffer sql = new StringBuffer();
		sql.append("\n delete from ").append(Banco.TABLE_NAME);
		sql.append("\n where ").append(Banco.BARS02_BANK_K).append("= ? ");
		
		int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {
			
			@Override
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;
				DAOHelper.setParameter(statement, i++, codigo);
				
			}
		});
		
		log.entering(CLASS_NAME, METHOD_NAME);
		
		return result;
	}

	@Override
	public int update(final Banco banco) {
		String METHOD_NAME = "update";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		StringBuffer sql = new StringBuffer();
		sql.append("\n update ").append(Banco.TABLE_NAME);
		sql.append("\n set ").append(Banco.BARS02_COUNTRY_C).append(" = ? ");
		sql.append("\n ,").append(Banco.BARS02_BANK_C).append(" = ? ");
		sql.append("\n ,").append(Banco.BARS02_BANK_AGENCY_C).append(" = ? ");
		sql.append("\n ,").append(Banco.BARS02_BANK_ACCOUNT_C).append(" = ? ");
		sql.append("\n ,").append(Banco.BARS02_DESCRIPTION_X).append(" = ? ");
		sql.append("\n ,").append(Banco.BARS02_CORPORATION_C).append(" = ? ");
		sql.append("\n ,").append(Banco.BARS02_ACCOUNT_C).append(" = ? ");
		sql.append("\n ,").append(Banco.BARS02_SUB_ACCOUNT_C).append(" = ? ");
		sql.append("\n ,").append(Banco.BARS02_STATUS_R).append(" = ? ");		
		sql.append("\n ,").append(Banco.BARS02_OPLOC_R).append(" = ? ");		
		sql.append("\n ,").append(Banco.BARS02_LAST_UPDT_USER_C).append(" = ? ");
		sql.append("\n ,").append(Banco.BARS02_LAST_UPDT_S).append(" = GETDATE() ");
		sql.append("\n where ").append(Banco.BARS02_BANK_K).append(" = ?");
				
		int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {
						
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;				
				DAOHelper.setParameter(statement, i++, banco.getPais());
				DAOHelper.setParameter(statement, i++, banco.getBanco());
				DAOHelper.setParameter(statement, i++, banco.getAgencia());
				DAOHelper.setParameter(statement, i++, banco.getContaCorrente());
				DAOHelper.setParameter(statement, i++, banco.getDescricao());
				DAOHelper.setParameter(statement, i++, banco.getCorporacao());			
				DAOHelper.setParameter(statement, i++, banco.getConta());			
				DAOHelper.setParameter(statement, i++, banco.getSubConta());
				DAOHelper.setParameter(statement, i++, banco.getStatus());
				DAOHelper.setParameter(statement, i++, banco.getCentroCusto());				
				DAOHelper.setParameter(statement, i++, banco.getUsuarioAlteracao());
				DAOHelper.setParameter(statement, i++, banco.getCod());
			}
		});
		
		log.exiting(CLASS_NAME, METHOD_NAME);
		
		return result;
	}

}
