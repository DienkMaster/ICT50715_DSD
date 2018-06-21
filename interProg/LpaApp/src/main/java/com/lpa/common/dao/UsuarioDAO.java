//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao;

import com.ford.bars.common.dao.base.DAOBase;
import com.ford.bars.model.Usuario;

/**
 * DAO para tabela JBARS04_USERS
 */
public interface UsuarioDAO extends DAOBase {

    /**
     * Pesquisa pela chave primaria da tabela
     * 
     * @param codigo
     * @return Usuario
     */
    Usuario findByCdsid(final String cdsid);

    /**
     * Inseri uma novo Usuario na base de dados
     * 
     * @param Usuario
     * @return resultado
     */
    int insert(final Usuario Usuario);

    /**
     * Deleta Usuario da base de dados
     * 
     * @param codigo
     * @return resultado
     */
    int delete(final String cdsid);

}
