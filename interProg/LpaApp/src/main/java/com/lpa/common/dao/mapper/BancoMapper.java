package com.lpa.common.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ford.bars.common.dao.base.MapperCallbackHandler;
import com.ford.bars.model.Area;
import com.ford.bars.model.Banco;

/**
 * @author lmantov1
 *
 */
public class BancoMapper implements MapperCallbackHandler<Banco> {


	@Override
	public Banco execute(ResultSet rs) throws SQLException {
		Banco bean = new Banco();
		bean.setCod(rs.getInt(Banco.BARS02_BANK_K));
		bean.setPais(rs.getInt(Banco.BARS02_COUNTRY_C));
		bean.setBanco(rs.getInt(Banco.BARS02_BANK_C));
		bean.setAgencia(rs.getInt(Banco.BARS02_BANK_AGENCY_C));
		bean.setContaCorrente(rs.getString(Banco.BARS02_BANK_ACCOUNT_C));
		bean.setDescricao(rs.getString(Banco.BARS02_DESCRIPTION_X));
		bean.setCorporacao(rs.getInt(Banco.BARS02_CORPORATION_C));
		bean.setConta(rs.getString(Banco.BARS02_ACCOUNT_C));
		bean.setSubConta(rs.getString(Banco.BARS02_SUB_ACCOUNT_C));
		bean.setStatus(rs.getInt(Banco.BARS02_STATUS_R));
		bean.setDataCriacao(rs.getDate(Banco.BARS02_CREATED_S));
		bean.setUsuarioCriacao(rs.getString(Banco.BARS02_CREATED_USER_C));
		bean.setDataAlteracao(rs.getDate(Banco.BARS02_LAST_UPDT_S));
		bean.setUsuarioAlteracao(rs.getString(Banco.BARS02_LAST_UPDT_USER_C));
		return bean;
	}

}
