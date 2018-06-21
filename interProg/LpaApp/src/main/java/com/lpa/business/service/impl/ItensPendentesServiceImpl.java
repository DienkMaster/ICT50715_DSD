//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.business.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.lpa.business.service.ItensPendentesService;
import com.ford.bars.common.dao.ItensPendentesDAO;
import com.ford.bars.common.dao.base.IConnectionManager;
import com.ford.bars.common.service.Command;
import com.ford.bars.common.service.impl.SqlServiceBaseImpl;
import com.ford.bars.model.FiltroItensPendentes;
import com.ford.bars.model.ItensPendentes;

@Named
public class ItensPendentesServiceImpl extends SqlServiceBaseImpl implements ItensPendentesService, Serializable {

    private static final long serialVersionUID = 5216085463860946563L;

    @Inject
    ItensPendentesDAO dao;

    public int delete(final int codigo) {

        final Command<Integer> command = new Command<Integer>() {
            @Override
            public Integer execute() {
                return dao.delete(codigo);
            }
        };

        return this.executeAndClose(command, dao);
    }

    public ItensPendentes findByPk(final int codigo) {
        final Command<ItensPendentes> command = new Command<ItensPendentes>() {
            @Override
            public ItensPendentes execute() {
                return dao.findByPk(codigo);
            }
        };

        return this.executeAndClose(command, dao);
    }

    public List<ItensPendentes> findByPendentes(final FiltroItensPendentes filtro) {

        final Command<List<ItensPendentes>> command = new Command<List<ItensPendentes>>() {
            @Override
            public List<ItensPendentes> execute() {
                return dao.findByPendentes(filtro);
            }
        };

        return this.executeAndClose(command, dao);
    }

    public int insert(final ItensPendentes item) {

        return this.executeAndClose(new Command<Integer>() {
            @Override
            public Integer execute() {
                return dao.insert(item);
            }
        }, dao);
    }

    public int updateConciliado(final ItensPendentes item) {
        final IConnectionManager connectionManager = this.getConnectionManager();

        final Command<Integer> command = new Command<Integer>() {
            @Override
            public Integer execute() {

                // adicionar tudo que precisa estar dentro da transacao aqui.
                // areaService.setConnectionManager(connectionManager);

                dao.setConnection(this.getConnection());

                return dao.updateConciliado(item);
            }
        };

        return this.transactionAndClose(command);
    }

}
