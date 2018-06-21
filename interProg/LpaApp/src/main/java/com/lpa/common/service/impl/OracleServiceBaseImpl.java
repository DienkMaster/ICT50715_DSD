package com.lpa.common.service.impl;

import com.ford.bars.common.dao.base.IConnectionManager;
import com.ford.bars.common.service.base.OracleServiceBase;
import com.ford.bars.common.service.base.ServiceBase;

public class OracleServiceBaseImpl extends ServiceBaseImpl implements ServiceBase, OracleServiceBase {

    public OracleServiceBaseImpl() {
        this.setJndiName("java:comp/env/jdbc/oracle");
    }

    @Override
    public IConnectionManager getConnectionManager() {
        // return getConnectionManager(DynapropConst.DYNAPROP_DATA_SOURCE_GROUP_ORACLE, DynapropConst.DYNAPROP_DATA_SOURCE_USER_ORACLE, DynapropConst.DYNAPROP_DATA_SOURCE_PASSWORD_ORACLE, null);
        return null;
    }

}
