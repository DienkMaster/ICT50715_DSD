//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.business.service;

import java.util.List;

import com.ford.bars.common.service.base.ServiceBase;
import com.ford.bars.model.Area;

public interface AreaService extends ServiceBase {

    Area findByPk(final int codigo);

    List<Area> findByFilter(final Area area);

    int insert(final Area area);

    int delete(final int codigo);

    int update(final Area area);

}
