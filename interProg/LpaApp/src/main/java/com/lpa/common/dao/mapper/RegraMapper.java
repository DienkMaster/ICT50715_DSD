package com.lpa.common.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ford.bars.common.dao.base.MapperCallbackHandler;
import com.ford.bars.model.Regra;

/**
 * @author lmantov1
 *
 */
public class RegraMapper implements MapperCallbackHandler<Regra> {

	
	public Regra execute(ResultSet rs) throws SQLException {
		
		Regra bean = new Regra();		
		bean.setCod(rs.getInt(Regra.BARS03_RULES_K));
		bean.setBanco(rs.getInt(Regra.BARS03_BARS02_BANK_K));
		bean.setParametroValor("S".equals(rs.getString(Regra.BARS03_VALUE_F)));
		bean.setParametroTransacao("S".equals(rs.getString(Regra.BARS03_TRANSACTION_F)));
		bean.setParametroDocumento("S".equals(rs.getString(Regra.BARS03_DOCUMENT_F)));
		bean.setParametroData("S".equals(rs.getString(Regra.BARS03_DATE_F)));
		bean.setParametroUm("S".equals(rs.getString(Regra.BARS03_PARAMETER1_F)));
		bean.setParametroDois("S".equals(rs.getString(Regra.BARS03_PARAMETER2_F)));
		bean.setParametroTres("S".equals(rs.getString(Regra.BARS03_PARAMETER3_F)));
		bean.setParametroQuatro("S".equals(rs.getString(Regra.BARS03_PARAMETER4_F)));
		bean.setParametroCinco("S".equals(rs.getString(Regra.BARS03_PARAMETER5_F)));
		bean.setNomeRegra(rs.getString(Regra.BARS03_REMARKS_X));
		bean.setAprovacao(rs.getInt(Regra.BARS03_BIND_TYPE_R));
		bean.setStatus(rs.getInt(Regra.BARS03_STATUS_R));
		bean.setDataCriacao(rs.getDate(Regra.BARS03_CREATED_S));
		bean.setUsuarioCriacao(rs.getString(Regra.BARS03_CREATED_USER_C));
		bean.setDataAlteracao(rs.getDate(Regra.BARS03_LAST_UPDT_S));
		bean.setUsuarioAlteracao(rs.getString(Regra.BARS03_LAST_UPDT_USER_C));
		return bean;
	}

}
