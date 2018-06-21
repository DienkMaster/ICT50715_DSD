//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.business.service;

import java.util.List;

import com.ford.bars.common.service.base.ServiceBase;
import com.ford.bars.model.FiltroItensPendentes;
import com.ford.bars.model.ItensPendentes;

public interface ItensPendentesService extends ServiceBase {

    ItensPendentes findByPk(final int codigo);

    List<ItensPendentes> findByPendentes(final FiltroItensPendentes filtro);

    int insert(final ItensPendentes item);

    int delete(final int codigo);

    int updateConciliado(final ItensPendentes item);

}
