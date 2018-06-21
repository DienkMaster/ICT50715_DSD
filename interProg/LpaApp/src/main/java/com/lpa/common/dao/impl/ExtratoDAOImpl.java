package com.lpa.common.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Named;

import com.lpa.common.dao.ExtratoDAO;
import com.ford.bars.common.dao.base.DAOHelper;
import com.ford.bars.common.dao.base.PrepareStatementCallbackHandler;
import com.ford.bars.model.Extrato;
import com.ford.it.logging.ILogger;
import com.ford.it.logging.LogFactory;

@Named
public class ExtratoDAOImpl extends DAOBaseImpl implements ExtratoDAO {
	
	  /** Logging. */
    private static final String CLASS_NAME = ExtratoDAOImpl.class.getName();
    /** Logging. */
    private static final ILogger log = LogFactory.getInstance().getLogger(CLASS_NAME);
	

	@Override
	public int insert(final Extrato extrato) {
		
		final String METHOD_NAME = "insert";
        log.entering(CLASS_NAME, METHOD_NAME);
        
        final StringBuffer sql = new StringBuffer();
        sql.append("\n insert into ").append(Extrato.TABLE_NAME);
        sql.append("\n (").append(Extrato.BARS06_BARS02_BANK_K);
        //sql.append("\n ,").append(Extrato.BARS06_BARS12_LOAD_K);
        sql.append("\n ,").append(Extrato.BARS06_HISTORY_X);
        sql.append("\n ,").append(Extrato.BARS06_DOCUMENT_R);
        sql.append("\n ,").append(Extrato.BARS06_DATE_S);
        sql.append("\n ,").append(Extrato.BARS06_VALUE_A);
        sql.append("\n ,").append(Extrato.BARS06_ACCOUNTING_TYPE_C);
        sql.append("\n ,").append(Extrato.BARS06_TRANSACTION_C);
        sql.append("\n ,").append(Extrato.BARS06_LOAD_TYPE_C);
        sql.append("\n ,").append(Extrato.BARS06_BINDING_F);
        sql.append("\n ,").append(Extrato.BARS06_BINDING_R);
        sql.append("\n ,").append(Extrato.BARS06_PARAMETER1_R);
        sql.append("\n ,").append(Extrato.BARS06_PARAMETER2_R);
        sql.append("\n ,").append(Extrato.BARS06_PARAMETER3_R);
        sql.append("\n ,").append(Extrato.BARS06_PARAMETER4_R);
        sql.append("\n ,").append(Extrato.BARS06_PARAMETER5_R);
        sql.append("\n ,").append(Extrato.BARS06_CREATED_USER_C);
        sql.append("\n ,").append(Extrato.BARS06_CREATED_S);
        sql.append("\n ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  GETDATE()) ");
        
        final int result = this.execute(sql.toString(), new PrepareStatementCallbackHandler() {
			
			@Override
			public void execute(PreparedStatement statement) throws SQLException {
				int i = 1;
				DAOHelper.setParameter(statement, i++, extrato.getBanco().getCod());
				//DAOHelper.setParameter(statement, i++, extrato.getRegra());
				DAOHelper.setParameter(statement, i++, extrato.getHistorico());
				DAOHelper.setParameter(statement, i++, extrato.getDocumento());
				DAOHelper.setParameter(statement, i++, extrato.getLancamento());
				DAOHelper.setParameter(statement, i++, extrato.getValor());
				DAOHelper.setParameter(statement, i++, extrato.getTipo());
				DAOHelper.setParameter(statement, i++, extrato.getTransacao());
				DAOHelper.setParameter(statement, i++, extrato.getTipoCarga());
				DAOHelper.setParameter(statement, i++, chekarParametro(extrato.isConciliado()));
				DAOHelper.setParameter(statement, i++, extrato.getAmarracao());
				DAOHelper.setParameter(statement, i++, extrato.getParametroUm());
				DAOHelper.setParameter(statement, i++, extrato.getParametroDois());
				DAOHelper.setParameter(statement, i++, extrato.getParametroTres());
				DAOHelper.setParameter(statement, i++, extrato.getParametroQuatro());
				DAOHelper.setParameter(statement, i++, extrato.getParametroCinco());
				DAOHelper.setParameter(statement, i++, extrato.getUsuarioCriacao());				
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

}
