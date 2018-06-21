//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.business.service.impl;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import com.lpa.business.service.UsuarioService;
import com.ford.bars.common.dao.UsuarioDAO;
import com.ford.bars.common.service.Command;
import com.ford.bars.common.service.impl.SqlServiceBaseImpl;
import com.ford.bars.model.Usuario;

@Named
public class UsuarioServiceImpl extends SqlServiceBaseImpl implements UsuarioService, Serializable {

    private static final long serialVersionUID = -2205213380312358631L;

    @Inject
    UsuarioDAO dao;

    public int delete(final String cdsid) {

        final Command<Integer> command = new Command<Integer>() {
            @Override
            public Integer execute() {
                return dao.delete(cdsid);
            }
        };

        return this.executeAndClose(command, dao);
    }

    public Usuario findByCdsid(final String cdsid) {
        final Command<Usuario> command = new Command<Usuario>() {
            @Override
            public Usuario execute() {
                return dao.findByCdsid(cdsid);
            }
        };

        return this.executeAndClose(command, dao);
    }

    public int insert(final Usuario obj) {

        return this.executeAndClose(new Command<Integer>() {
            @Override
            public Integer execute() {
                return dao.insert(obj);
            }
        }, dao);
    }

    public int update(final Usuario obj) {

        final Command<Integer> command = new Command<Integer>() {
            @Override
            public Integer execute() {
                dao.setConnection(this.getConnection());

                dao.delete(obj.getCdsid());

                return dao.insert(obj);
            }
        };

        return this.transactionAndClose(command, dao);
    }

}
