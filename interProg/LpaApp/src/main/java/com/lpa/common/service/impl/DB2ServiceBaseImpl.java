package com.lpa.common.service.impl;

import com.ford.bars.common.dao.base.IConnectionManager;
import com.ford.bars.common.service.base.DB2ServiceBase;

public class DB2ServiceBaseImpl extends ServiceBaseImpl implements DB2ServiceBase {

    public DB2ServiceBaseImpl() {
        this.setJndiName("java:comp/env/jdbc/db2");
    }

    @Override
    public IConnectionManager getConnectionManager() {
        // return getConnectionManager(DynapropConst.DYNAPROP_DATA_SOURCE_GROUP_DB2, DynapropConst.DYNAPROP_DATA_SOURCE_USER_DB2, DynapropConst.DYNAPROP_DATA_SOURCE_PASSWORD_DB2, null);
        return null;
    }

}
