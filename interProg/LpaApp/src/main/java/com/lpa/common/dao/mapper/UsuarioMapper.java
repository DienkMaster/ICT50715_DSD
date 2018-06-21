//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ford.bars.common.dao.base.MapperCallbackHandler;
import com.ford.bars.model.Usuario;

/**
 * Classe responsavel por fazer o mapeamento do ResultSet para o Objeto
 */
public class UsuarioMapper implements MapperCallbackHandler<Usuario> {

    public Usuario execute(final ResultSet rs) throws SQLException {
        final Usuario bean = new Usuario();

        bean.setCod(rs.getInt(Usuario.BARS04_USER_K));
        bean.setPais(rs.getInt(Usuario.BARS04_COUNTRY_C));
        bean.setCdsid(rs.getString(Usuario.BARS04_CDSID_C));
        bean.setTema(rs.getString(Usuario.BARS04_THEME_C));
        bean.setIdioma(rs.getString(Usuario.BARS04_LANGUAGE_C));
        bean.setMoeda(rs.getString(Usuario.BARS04_CURRENCY_C));
        bean.setTemplate(rs.getString(Usuario.BARS04_TEMPLATE_X));
        bean.setEstilo(rs.getInt(Usuario.BARS04_STYLE_R));
        bean.setDataCriacao(rs.getDate(Usuario.BARS04_CREATED_S));
        bean.setUsuarioCriacao(rs.getString(Usuario.BARS04_CREATED_USER_C));
        bean.setDataAlteracao(rs.getDate(Usuario.BARS04_LAST_UPDT_S));
        bean.setUsuarioAlteracao(rs.getString(Usuario.BARS04_LAST_UPDT_USER_C));

        return bean;
    }

}
