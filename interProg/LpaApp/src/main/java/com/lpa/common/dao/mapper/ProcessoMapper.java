package com.lpa.common.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.ford.bars.common.dao.base.MapperCallbackHandler;
import com.ford.bars.model.Processo;

/**
 * @author lmantov1
 *
 */
public class ProcessoMapper implements MapperCallbackHandler<Processo> {

	@Override
	public Processo execute(final ResultSet rs) throws SQLException {
		final Processo bean = new Processo();
		
		bean.setCod(rs.getInt(Processo.BARS05_PROCESS_K));
		bean.setPais(rs.getInt(Processo.BARS05_COUNTRY_C));
		bean.setDescricao(rs.getString(Processo.BARS05_PROCESS_X));
		bean.setLog(rs.getString(Processo.BARS05_LOG_X));
		bean.setErro(rs.getString(Processo.BARS05_ERROR_X));
		bean.setStatus(rs.getInt(Processo.BARS05_STATUS_R));
		bean.setIniciado(rs.getDate(Processo.BARS05_START_S));
		bean.setFinalizado(rs.getDate(Processo.BARS05_FINISH_S));
		
		return bean;
	}

}
