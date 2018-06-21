//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.business.service.impl;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.lpa.business.service.AreaService;
import com.ford.bars.common.dao.AreaDAO;
import com.ford.bars.common.service.Command;
import com.ford.bars.common.service.impl.SqlServiceBaseImpl;
import com.ford.bars.model.Area;

@Named
public class AreaServiceImpl extends SqlServiceBaseImpl implements AreaService, Serializable {

    private static final long serialVersionUID = -2205213380312358631L;

    @Inject
    AreaDAO dao;

    public int delete(final int codigo) {

        final Command<Integer> command = new Command<Integer>() {
            @Override
            public Integer execute() {
                return dao.delete(codigo);
            }
        };

        return this.executeAndClose(command, dao);
    }

    public Area findByPk(final int codigo) {
        final Command<Area> command = new Command<Area>() {
            @Override
            public Area execute() {
                return dao.findByPk(codigo);
            }
        };

        return this.executeAndClose(command, dao);
    }

    public List<Area> findByFilter(final Area area) {

        final Command<List<Area>> command = new Command<List<Area>>() {
            @Override
            public List<Area> execute() {
                return dao.findByFilter(area);
            }
        };

        return this.executeAndClose(command, dao);
    }

    public int insert(final Area area) {

        return this.executeAndClose(new Command<Integer>() {
            @Override
            public Integer execute() {
                return dao.insert(area);
            }
        }, dao);
    }

    public int update(final Area area) {

        final Command<Integer> command = new Command<Integer>() {
            @Override
            public Integer execute() {
                dao.setConnection(this.getConnection());
                return dao.update(area);
            }
        };

        return this.executeAndClose(command);
    }

}
