//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.business.service;

import com.ford.bars.common.service.base.ServiceBase;
import com.ford.bars.model.Usuario;

public interface UsuarioService extends ServiceBase {

    Usuario findByCdsid(final String cdsid);

    int insert(final Usuario obj);

    int update(final Usuario obj);

    int delete(final String cdsid);

}
