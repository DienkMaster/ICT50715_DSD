//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ford.bars.common.dao.base.MapperCallbackHandler;
import com.ford.bars.model.Area;
import com.ford.bars.model.Banco;
import com.ford.bars.model.Extrato;
import com.ford.bars.model.ItensPendentes;
import com.ford.bars.model.Razao;

/**
 * Classe responsavel por fazer o mapeamento do ResultSet para o Objeto
 */
public class ItensPendentesMapper implements MapperCallbackHandler<ItensPendentes> {

    /**
     * @see com.ford.it.ceps.data.MapperCallbackHandler#execute(java.sql.ResultSet)
     */
    public ItensPendentes execute(final ResultSet rs) throws SQLException {

        final ItensPendentes bean = new ItensPendentes();

        final Extrato extrato = new Extrato();
        final Razao razao = new Razao();
        final Area area = new Area();
        final Banco banco = new Banco();

        bean.setCodigo(rs.getInt(ItensPendentes.BARS08_ITEM_K));
        bean.setTipo(rs.getString(ItensPendentes.BARS08_TYPE_C));
        bean.setSugestao(rs.getString(ItensPendentes.BARS08_RESPONSE_X));

        final String conciliado = rs.getString(ItensPendentes.BARS08_BINDING_F);

        if (conciliado != null && conciliado.equalsIgnoreCase("S")) {
            bean.setConciliado(true);
        } else {
            bean.setConciliado(false);
        }

        bean.setEnvioEmail(rs.getDate(ItensPendentes.BARS08_EMAIL_S));
        bean.setDataCriacao(rs.getDate(ItensPendentes.BARS08_CREATED_S));
        bean.setUsuarioCriacao(rs.getString(ItensPendentes.BARS08_CREATED_USER_C));
        bean.setDataAlteracao(rs.getDate(ItensPendentes.BARS08_LAST_UPDT_S));
        bean.setUsuarioAlteracao(rs.getString(ItensPendentes.BARS08_LAST_UPDT_USER_C));

        if (bean.getTipo().equalsIgnoreCase("E")) {

            // relacionamento com tabela de extrato bancario
            extrato.setCod(rs.getInt(Extrato.BARS06_BANK_STATEMENT_K));
            extrato.setHistorico(rs.getString(Extrato.BARS06_HISTORY_X));
            extrato.setDocumento(rs.getInt(Extrato.BARS06_DOCUMENT_R));
            extrato.setLancamento(rs.getDate(Extrato.BARS06_DATE_S));
            extrato.setValor(rs.getBigDecimal(Extrato.BARS06_VALUE_A));
            extrato.setTipo(rs.getString(Extrato.BARS06_ACCOUNTING_TYPE_C));

            banco.setCod(rs.getInt("BancoExtrato"));
            banco.setCorporacao(rs.getInt("CorporacaoExtrato"));
            banco.setConta(rs.getString("ContaExtrato"));
            banco.setSubConta(rs.getString("SubContaExtrato"));
            extrato.setBanco(banco);
            bean.setExtrato(extrato);

        } else {

            // relacionamento com arquivo razao recebido
            razao.setCod(rs.getInt(Razao.BARS07_LEDGER_K));
            razao.setHistorico(rs.getString(Razao.BARS07_HISTORY_X));
            razao.setDocumento(rs.getInt(Razao.BARS07_DOCUMENT_R));
            razao.setDataProcesso(rs.getDate(Razao.BARS07_DATE_S));
            razao.setValor(rs.getBigDecimal(Razao.BARS07_VALUE_A));
            razao.setTipo(rs.getString(Razao.BARS07_ACCOUNTING_TYPE_C));
            razao.setJournalID(rs.getString(Razao.BARS07_JOURNAL_ID_C));

            banco.setCod(rs.getInt("BancoRazao"));
            banco.setCorporacao(rs.getInt("CorporacaoRazao"));
            banco.setConta(rs.getString("ContaRazao"));
            banco.setSubConta(rs.getString("SubContaRazao"));
            razao.setBanco(banco);

            bean.setRazao(razao);

        }

        // relacionamento com tabela de area
        area.setCod(rs.getInt(Area.BARS01_AREA_K));
        area.setDescricao(rs.getString(Area.BARS01_DESCRIPTION_X));
        area.setArea(rs.getString(Area.BARS01_BUSINESS_AREA_C));
        bean.setArea(area);

        return bean;
    }

}
