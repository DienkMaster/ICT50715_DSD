//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao;

import java.util.List;

import com.ford.bars.common.dao.base.DAOBase;
import com.ford.bars.model.FiltroItensPendentes;
import com.ford.bars.model.ItensPendentes;

/**
 * DAO para tabela JBARS08_PENDING_ITEMS
 */
public interface ItensPendentesDAO extends DAOBase {

    /**
     * Pesquisa pela chave primaria da tabela
     * 
     * @param codigo
     * @return ItensPendentes
     */
    ItensPendentes findByPk(final int codigo);

    /**
     * Pesquisa pelos filtros das tela
     * 
     * @param Area
     * @return List<ItensPendentes>
     */
    List<ItensPendentes> findByPendentes(final FiltroItensPendentes filtro);

    /**
     * Inseri uma nova Area na base de dados
     * 
     * @param ItensPendentes
     * @return resultado
     */
    int insert(final ItensPendentes item);

    /**
     * Deleta ItensPendentes da base de dados
     * 
     * @param codigo
     * @return resultado
     */
    int delete(final int codigo);

    /**
     * Atualiza ItensPendentes na base de dados
     * 
     * @param ItensPendentes
     * @return resultado
     */
    int updateConciliado(final ItensPendentes item);

}
